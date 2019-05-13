package requests.spark.websockets.objects;

import core.builders.requests.WebSocketMessageMapping;
import data.model.objects.json.JSONContainer;
import error.Error;
import org.apache.log4j.Logger;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;
import requests.spark.websockets.WebSocketManager;
import requests.spark.websockets.WebSocketSession;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Message {
    private static Logger log = Logger.getLogger(Message.class);

    public static final int ALL_PLAYERS = 1;
    public static final int ALL_ADMINS = 2;
    public static final int ALL = 3;

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

    // Used to populate incoming data into the message object to be used
    public void populate(JSONObject jsonObject) {

    }

    public void process() {

    }

    public void prepareToSend() {

    }

    public String getType() {
        return type;
    }

    public String getCallBackUUID() {
        return callBackUUID;
    }

    public void setType(String type) {
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

    public void sendTo(int audience) {
        try {
            addResponseData("type", type);
            prepareToSend();

            List<WebSocketSession> audienceList = new ArrayList<>();

            if (audience == ALL_ADMINS) {
                audienceList = WebSocketManager.getInstance().getAdmins();
            } else if (audience == ALL_PLAYERS) {
                audienceList = WebSocketManager.getInstance().getPlayers();
            }

            JSONContainer jsonContainer = new JSONContainer(jsonResponse);
            for (WebSocketSession connection : audienceList) {
                Session session = connection.getSession();
                if (session.isOpen()) {
                    session.getRemote().sendString(jsonContainer.writeResponse());
                }
            }

        } catch (IOException ex) {
            Error.WEBSOCKET_RESPONSE_EXCEPTION.record().create(ex);
        }
    }

    public static <MessageObject extends Message> MessageObject create(Class<MessageObject> clazz) {
        if (clazz != null) {
            MessageObject message = null;
            try {
                message = clazz.getConstructor().newInstance();
                MessageType messageType = clazz.getAnnotation(MessageType.class);
                message.setType(messageType.value());
            } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException ex) {
                Error.GENERIC_WEBSOCKET_EXCEPTION.record().create(ex);
            }

            return message;
        }
        return null;
    }
}
