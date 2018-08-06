package requests.spark;

import data.model.objects.json.JSONContainer;
import spark.Request;

public class SparkRequest {
    public JSONContainer get(Request request) {
        return new JSONContainer().error("Not Found").status(404);
    }

    public JSONContainer post(Request request) {
        return new JSONContainer().error("Not Found").status(404);
    }

    public JSONContainer put(Request request) {
        return new JSONContainer().error("Not Found").status(404);
    }

    public JSONContainer delete(Request request) {
        return new JSONContainer().error("Not Found").status(404);
    }
}
