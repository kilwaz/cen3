package core.builders.requests;

import data.model.objects.json.JSONContainer;
import error.Error;
import org.apache.log4j.Logger;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import requests.annotations.RequestName;
import requests.spark.SparkRequest;
import spark.Response;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import static spark.Spark.*;

public class RequestMapper {
    private static Logger log = Logger.getLogger(RequestMapper.class);

    public static void buildMappings() {
        port(4568);

        Set<Class<?>> webSockets = new Reflections("requests.spark.websockets").getTypesAnnotatedWith(WebSocket.class);
        webSockets.forEach(RequestMapper::buildWebsocket);

        Set<Class<? extends SparkRequest>> spark = new Reflections("requests.spark.requests", new SubTypesScanner(false)).getSubTypesOf(SparkRequest.class);
        spark.forEach(RequestMapper::buildRequest);

        get("/", (request, response) -> "Yeah this is something, but it isn't handled");
    }

    private static void buildWebsocket(Class<?> requestClass) {
        log.info("Building spark websocket of " + requestClass.getName());

        webSocket("/" + requestClass.getAnnotation(RequestName.class).value(), requestClass);
    }

    private static void buildRequest(Class<? extends SparkRequest> requestClass) {
        RequestName requestNameAnnotation = requestClass.getAnnotation(RequestName.class);

        log.info("Building spark request of " + requestClass.getName());

        try {
            SparkRequest sparkRequest = requestClass.getConstructor().newInstance();

            // GET
            get("/" + requestNameAnnotation.value(), (request, response) -> {
                log.info("GET " + requestNameAnnotation.value());
                try {
                    response.type("application/json");

                    JSONContainer jsonContainer = sparkRequest.get(request);
                    jsonContainer.filter(request.queryParams("filter"));
                    return handleResponse(jsonContainer, response);
                } catch (Exception ex) {
                    Error.GET_EXCEPTION.record().additionalInformation("URL: " + request.url()).create(ex);
                }
                response.status(500);
                return "ERROR";
            });

            // POST
            post("/" + requestNameAnnotation.value(), (request, response) -> {
                try {
                    log.info("POST " + requestNameAnnotation.value());
                    response.type("application/json");
                    return handleResponse(sparkRequest.post(request), response);
                } catch (Exception ex) {
                    Error.POST_EXCEPTION.record().additionalInformation("URL: " + request.url()).create(ex);
                }
                response.status(500);
                return "ERROR";
            });

            // PUT
            put("/" + requestNameAnnotation.value(), (request, response) -> {
                try {
                    log.info("PUT " + requestNameAnnotation.value());
                    response.type("application/json");
                    return handleResponse(sparkRequest.put(request), response);
                } catch (Exception ex) {
                    Error.PUT_EXCEPTION.record().additionalInformation("URL: " + request.url()).create(ex);
                }
                response.status(500);
                return "ERROR";
            });

            // DELETE
            delete("/" + requestNameAnnotation.value(), (request, response) -> {
                try {

                    log.info("DELETE " + requestNameAnnotation.value());
                    response.type("application/json");
                    return handleResponse(sparkRequest.delete(request), response);
                } catch (Exception ex) {
                    Error.DELETE_EXCEPTION.record().additionalInformation("URL: " + request.url()).create(ex);
                }
                response.status(500);
                return "ERROR";
            });

            options("/" + requestNameAnnotation.value(), (request, response) -> {
                try {
                    log.info("OPTIONS " + requestNameAnnotation.value());
                    return "";
                } catch (Exception ex) {
                    Error.OPTIONS_EXCEPTION.record().additionalInformation("URL: " + request.url()).create(ex);
                }
                response.status(500);
                return "ERROR";
            });
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            Error.GENERIC_REQUEST_EXCEPTION.record().create(ex);
        }
    }

    private static String handleResponse(JSONContainer jsonContainer, Response response) {
        if (jsonContainer.isError()) {
            if (jsonContainer.getStatus() != null) {
                response.status(jsonContainer.getStatus());
            } else {
                response.status(400);
            }
            return jsonContainer.writeResponse();
        } else {
            return jsonContainer.writeResponse();
        }
    }
}
