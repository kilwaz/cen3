package requests.spark;

import data.model.objects.json.JSONContainer;
import org.json.JSONObject;
import spark.Request;

public class SparkRequest {
    public static Integer REQUEST_ALL = 1;
    public static Integer REQUEST_INDV = 2;

    public JSONContainer get(Request request) {
        return new JSONContainer();
    }
}
