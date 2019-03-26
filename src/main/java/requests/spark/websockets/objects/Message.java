package requests.spark.websockets.objects;

import core.builders.requests.WebSocketMessageMapping;
import data.model.objects.json.JSONContainer;
import error.Error;
import org.apache.log4j.Logger;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Message {
    private static Logger log = Logger.getLogger(Message.class);

    private String type;
    private String callBackUUID;

    public static Message decode(JSONContainer jsonContainer) {
        JSONObject jsonObject = jsonContainer.toJSONObject();

        Class mappingClass = WebSocketMessageMapping.mappingClass(jsonObject.getString("_type"));

        try {
            if (mappingClass != null) {
                Message message = (Message) mappingClass.getConstructor().newInstance();
                message.setType(jsonObject.getString("_type"));
                message.setCallBackUUID(jsonObject.getString("_callbackUUID"));
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

    public void process(Session session) throws IOException {

    }

    public String getType() {
        return type;
    }

    public String getCallBackUUID() {
        return callBackUUID;
    }

    void setType(String type) {
        this.type = type;
    }

    void setCallBackUUID(String callBackUUID) {
        this.callBackUUID = callBackUUID;
    }
}
