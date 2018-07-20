package requests.spark;

import data.model.objects.json.JSONContainer;
import org.json.JSONObject;
import requests.annotations.RequestName;

import java.util.UUID;

@RequestName("source")
public class Source extends SparkRequest {
    public JSONContainer get(JSONObject jsonObject) {
        if (jsonObject.has("uuid")) {
            String uuid = jsonObject.getString("uuid");
            if (!uuid.isEmpty()) {
                data.model.objects.Source source = data.model.objects.Source.load(UUID.fromString(uuid), data.model.objects.Source.class);

                JSONContainer jsonContainer = new JSONContainer();
                jsonContainer.dbDataItem(source);
                return jsonContainer;
            }
        }

        return new JSONContainer();
    }

//    public JSONContainer post() {
//
//    }
}
