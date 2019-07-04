package requests.spark.websockets.objects;

import core.builders.requests.WebSocketMessageMapping;
import data.model.objects.json.JSONContainer;
import error.Error;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import requests.spark.websockets.WebSocketManager;
import requests.spark.websockets.WebSocketSession;
import requests.spark.websockets.objects.messages.dataobjects.WebSocketData;
import requests.spark.websockets.objects.messages.mapping.WSDataIncoming;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WebSocketAction<WSMessage extends Message, WSData extends WebSocketData> {
    public static final int ALL_PLAYERS = 1;
    public static final int ALL_ADMINS = 2;
    public static final int ALL_GAME_MASTERS = 3;
    public static final int ALL = 4;

    public void push(WSMessage wsMessage, int audience) {
        try {
            List<WebSocketSession> audienceList = new ArrayList<>();

            if (audience == ALL_ADMINS) {
                audienceList = WebSocketManager.getInstance().getAdmins();
            } else if (audience == ALL_PLAYERS) {
                audienceList = WebSocketManager.getInstance().getPlayers();
            } else if (audience == ALL_GAME_MASTERS) {
                audienceList = WebSocketManager.getInstance().getGameMasters();
            } else if (audience == ALL) {
                audienceList = WebSocketManager.getInstance().getAllSessions();
            }

            JSONContainer jsonContainer = response(wsMessage);
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

    public WSMessage request(JSONContainer jsonContainerDecoder) {
        try {
            JSONObject jsonObjectDecoded = jsonContainerDecoder.toJSONObject();

            Class messageClass = WebSocketMessageMapping.messageClass(jsonObjectDecoded.getString("_type"));

            WebSocketDataClass webSocketDataClass = (WebSocketDataClass) messageClass.getAnnotation(WebSocketDataClass.class);
            Class dataClass = webSocketDataClass.value();

            WSMessage wsMessage = (WSMessage) messageClass.getConstructor().newInstance();
            WSData wsData = (WSData) dataClass.getConstructor().newInstance();

            Set<Field> incomingDataFields = new Reflections(dataClass, new FieldAnnotationsScanner()).getFieldsAnnotatedWith(WSDataIncoming.class);
            for (Field field : incomingDataFields) {
                String fieldName = field.getName();
                String capFieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

                try {
                    Method fieldMethod = dataClass.getMethod("set" + capFieldName, String.class);
                    fieldMethod.invoke(wsData, jsonObjectDecoded.getString("_" + fieldName));
                } catch (NoSuchMethodException ex) {
                    ex.printStackTrace();
                }
            }

            wsMessage.webSocketData(wsData);
            return wsMessage;
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException ex) {
            //TODO: Make this a real error
            ex.printStackTrace();
        }

        return null;
    }

    public JSONContainer response(WSMessage wsMessage) {
        try {
            WebSocketDataClass webSocketDataClass = wsMessage.getClass().getAnnotation(WebSocketDataClass.class);
            Class dataClass = webSocketDataClass.value();
            WSData wsData = (WSData) wsMessage.getWebSocketData();

            Set<Field> outgoingDataFields = new Reflections(dataClass, new FieldAnnotationsScanner()).getFieldsAnnotatedWith(WSDataOutgoing.class);
            JSONObject jsonObject = new JSONObject();
            for (Field field : outgoingDataFields) {
                String fieldName = field.getName();
                String capFieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

                try {
                    Method fieldMethod = dataClass.getMethod("get" + capFieldName);
                    jsonObject.put("" + fieldName, fieldMethod.invoke(wsData));
                } catch (NoSuchMethodException ex) {
                    ex.printStackTrace();
                }
            }

            return new JSONContainer(jsonObject);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            //TODO: Make this a real error
            ex.printStackTrace();
        }

        return new JSONContainer();
    }
}
