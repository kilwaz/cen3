package requests.spark;

import data.model.objects.Source;
import data.model.objects.json.JSONContainer;
import requests.annotations.RequestName;
import spark.Request;
import utils.managers.DatabaseObjectManager;

import java.util.UUID;

@RequestName("encodedProgress")
public class EncodedProgressAPI extends SparkRequest {
    public JSONContainer get(Request request) {
        String uuidString = request.queryParams("uuid");

        if (uuidString != null) {
            UUID uuid;
            try {
                uuid = UUID.fromString(request.queryParams("uuid"));
            } catch (IllegalArgumentException ex) {
                return new JSONContainer().error("Invalid UUID");
            }

            if (DatabaseObjectManager.getInstance().objectExists(uuid)) { // Check if UUID is valid
                Source source = Source.load(uuid, Source.class);

                JSONContainer jsonContainer = new JSONContainer();
                jsonContainer.dbDataItem(source.getEncodedProgress());
                return jsonContainer; // RETURN SINGLE SOURCE
            } else {
                return new JSONContainer().error("UUID Not found"); // RETURN NO RESULT
            }
        }

        return new JSONContainer().error("UUID Not Valid");
    }
}
