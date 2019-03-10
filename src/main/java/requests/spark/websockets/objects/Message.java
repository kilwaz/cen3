package requests.spark.websockets.objects;

import data.model.objects.json.JSONContainer;
import org.json.JSONObject;

public class Message extends JSONContainer {
    private String type;
    private String message;

    public Message(JSONObject jsonObject) {
        this.type = jsonObject.getString("type");
        this.message = jsonObject.getString("message");
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
