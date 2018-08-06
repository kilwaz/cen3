package core.builders.requests;

import data.model.objects.json.JSONContainer;
import org.apache.log4j.Logger;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import requests.annotations.RequestName;
import requests.spark.SparkRequest;
import spark.Response;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Set;

import static spark.Spark.*;

public class RequestMapper {
    private static Logger log = Logger.getLogger(RequestMapper.class);

    private static HashMap<String, RequestMapping> mapping = new HashMap<>();

    public static void buildMappings() {
        Set<Class<? extends SparkRequest>> spark = new Reflections("requests.spark", new SubTypesScanner(false)).getSubTypesOf(SparkRequest.class);
        spark.forEach(RequestMapper::buildSpark);

        get("/", (request, response) -> "Yeah this is something");
    }

    private static void buildSpark(Class<? extends SparkRequest> requestClass) {
        RequestName requestNameAnnotation = requestClass.getAnnotation(RequestName.class);

        log.info("Building spark request of " + requestClass.getName());

        try {
            SparkRequest sparkRequest = requestClass.getConstructor().newInstance();

            // GET
            get("/" + requestNameAnnotation.value(), (request, response) -> {
                try {
                    response.type("application/json");

//                    String json = request.queryParams("json");
//                    JSONContainer incomingRequestData = new JSONContainer(json);
//                    JSONObject jsonObject = incomingRequestData.toJSONObject();

                    JSONContainer jsonContainer = sparkRequest.get(request);
                    jsonContainer.filter(request.queryParams("filter"));
                    return handleResponse(jsonContainer, response);
                } catch (Exception ex) {
                    log.info("err", ex);
                }
                response.status(500);
                return "ERROR";
            });

            // POST
            post("/" + requestNameAnnotation.value(), (request, response) -> {
                response.type("application/json");
                return handleResponse(sparkRequest.post(request), response);
            });

            // PUT
            put("/" + requestNameAnnotation.value(), (request, response) -> {
                response.type("application/json");
                return handleResponse(sparkRequest.put(request), response);
            });

            // PUT
            delete("/" + requestNameAnnotation.value(), (request, response) -> {
                response.type("application/json");
                return handleResponse(sparkRequest.delete(request), response);
            });

            options("/" + requestNameAnnotation.value(), (request, response) -> {
                return "";
            });
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            ex.printStackTrace();
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
