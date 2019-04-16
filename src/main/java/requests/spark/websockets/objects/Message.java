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
    private Session session;

    private JSONObject jsonResponse = new JSONObject();

    public static Message decode(JSONContainer jsonContainerDecoder) {
        JSONObject jsonObjectDecoded = jsonContainerDecoder.toJSONObject();

        Class mappingClass = WebSocketMessageMapping.mappingClass(jsonObjectDecoded.getString("_type"));

        try {
            if (mappingClass != null) {
                Message message = (Message) mappingClass.getConstructor().newInstance();
                message.setType(jsonObjectDecoded.getString("_type"));
                message.setCallBackUUID(jsonObjectDecoded.getString("_callbackUUID"));
                message.populate(jsonObjectDecoded);
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

    public String getCallBackUUID() {
        return callBackUUID;
    }

    void setType(String type) {
        this.type = type;
    }

    void setCallBackUUID(String callBackUUID) {
        this.callBackUUID = callBackUUID;
    }

    public Session getSession() {
        return session;
    }

    public Message session(Session session) {
        this.session = session;
        return this;
    }

    public void addResponseData(String name, Object value) {
        jsonResponse.put(name, value);
    }

    public void handleResponse() {
        try {
            addResponseData("type", this.getClass().getSimpleName());
            addResponseData("callBackUUID", this.getCallBackUUID());

            JSONContainer jsonContainer = new JSONContainer(jsonResponse);
            getSession().getRemote().sendString(jsonContainer.writeResponse());
        } catch (IOException ex) {
            Error.WEBSOCKET_RESPONSE_EXCEPTION.record().create(ex);
        }
    }
}
