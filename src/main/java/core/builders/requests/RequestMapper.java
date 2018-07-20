package core.builders.requests;

import data.model.objects.json.JSONContainer;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import requests.annotations.RequestName;
import requests.spark.SparkRequest;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Set;

import static spark.Spark.get;

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

            get("/" + requestNameAnnotation.value(), (request, response) -> {
                try {
                    response.type("application/json");

                    String json = request.queryParams("json");
                    JSONContainer incomingRequestData = new JSONContainer(json);
                    JSONObject jsonObject = incomingRequestData.toJSONObject();

                    JSONContainer jsonContainer = sparkRequest.get(jsonObject);
                    return jsonContainer.writeResponse();
                } catch (Exception ex) {
                    log.info("err", ex);
                }

                return "ERROR";
            });
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            ex.printStackTrace();
        }
    }
}
