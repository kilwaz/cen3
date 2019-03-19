package requests.spark.websockets.objects;

import core.builders.requests.WebSocketMessageMapping;
import data.model.objects.json.JSONContainer;
import error.Error;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;

public class Message {
    private String type;

    public static Message decode(JSONContainer jsonContainer) {
        JSONObject jsonObject = jsonContainer.toJSONObject();

        Class mappingClass = WebSocketMessageMapping.mappingClass(jsonObject.getString("type"));

        try {
            if (mappingClass != null) {
                Message message = (Message) mappingClass.getConstructor().newInstance();
                message.setType(jsonObject.getString("type"));
                message.populate(jsonObject);
                return message;
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            Error.GENERIC_WEBSOCKET_EXCEPTION.record().create(ex);
        }
        return null;
    }

    public void populate(JSONObject jsonObject) {

    }

    public void process() {

    }

    public String getType() {
        return type;
    }

    void setType(String type) {
        this.type = type;
    }
}
