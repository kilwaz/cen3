package requests.spark.websockets.objects;

import core.builders.requests.WebSocketMessageMapping;
import data.model.objects.json.JSONContainer;
import error.Error;
import log.AppLogger;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONArray;
import org.json.JSONObject;
import requests.spark.websockets.WebSocketSession;
import requests.spark.websockets.objects.messages.dataobjects.WebSocketData;
import requests.spark.websockets.objects.messages.mapping.*;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class WebSocketAction<WSMessage extends Message, WSData extends WebSocketData, WSJsonWeb extends requests.spark.websockets.objects.JSONWeb> {
    Logger log = AppLogger.logger();

    public void push(Push push) {
        try {
            JSONContainer jsonContainer = response((WSMessage) push.getPushedMessage());
            for (WebSocketSession connection : push.getAudience().audience()) {
                Session session = connection.getSession();
                if (session.isOpen()) {
                    String response = jsonContainer.writeResponse();
                    if (!response.contains("HeartBeat")) {
                        log.info("<- Outgoing message " + response);
                    }
                    session.getRemote().sendString(response);
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

            ArrayList<Field> incomingDataFields = new ArrayList<>();
            // Data class
            Field[] fields = dataClass.getDeclaredFields();
            for (Field field : fields) {
                for (Annotation annotation : field.getDeclaredAnnotations()) {
                    if (annotation instanceof WSDataIncoming) {
                        incomingDataFields.add(field);
                        break;
                    }
                }
            }

            // Super class
            fields = dataClass.getSuperclass().getDeclaredFields();
            for (Field field : fields) {
                for (Annotation annotation : field.getDeclaredAnnotations()) {
                    if (annotation instanceof WSDataIncoming) {
                        incomingDataFields.add(field);
                        break;
                    }
                }
            }

            for (Field field : incomingDataFields) {
                String fieldName = field.getName();
                String capFieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

                try {
                    if (field.isAnnotationPresent(WSDataJSONArrayClass.class)) {
                        Method fieldMethod = dataClass.getMethod("set" + capFieldName, JSONArray.class);
                        fieldMethod.invoke(wsData, jsonObjectDecoded.getJSONArray("_" + fieldName));
                    } else if (field.isAnnotationPresent(WSDataTypeScriptClass.class)) {
                        WSJsonWeb jsonWeb = (WSJsonWeb) field.getAnnotation(WSDataTypeScriptClass.class).value().getConstructor().newInstance();
                        if (jsonObjectDecoded.has("_" + fieldName)) {
                            jsonWeb.populateFromJSON(jsonObjectDecoded.getJSONObject("_" + fieldName));
                            Method fieldMethod = dataClass.getMethod("set" + capFieldName, field.getAnnotation(WSDataTypeScriptClass.class).value());
                            fieldMethod.invoke(wsData, jsonWeb);
                        } else {
                            log.info("Expected incoming JSON field of " + "_" + fieldName + " has not been found on the object");
                        }
                    } else if (field.getType() == Integer.class) { // Integer
                        Method fieldMethod = dataClass.getMethod("set" + capFieldName, Integer.class);
                        fieldMethod.invoke(wsData, jsonObjectDecoded.getInt("_" + fieldName));
                    } else if (field.getType() == String.class) { // String
                        Method fieldMethod = dataClass.getMethod("set" + capFieldName, String.class);
                        fieldMethod.invoke(wsData, jsonObjectDecoded.getString("_" + fieldName));
                    } else { // Attempt to try String if everything else fails
                        Error.WEBSOCKET_PARSE_METHOD.record().additionalInformation("Could not find matching method class for " + capFieldName + " with class " + field.getType()).create();
                    }
                } catch (NoSuchMethodException ex) {
                    Error.WEBSOCKET_PARSE_METHOD.record().additionalInformation("Variable name is " + capFieldName).create(ex);
                } catch (org.json.JSONException ex) {
                    log.info(ex.getMessage());
                }
            }

            wsMessage.webSocketData(wsData);
            return wsMessage;
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException |
                 InstantiationException ex) {
            //TODO: Make this a real error
            ex.printStackTrace();
        }

        return null;
    }

    public JSONContainer response(WSMessage wsMessage) {
        try {
            Class dataClass;
            //TODO:Review this way of getting data classes?
            if (wsMessage instanceof PushedMessage) { // Push messages already have their data classes assigned to them
                dataClass = wsMessage.getWebSocketData().getClass();
            } else { // Response messages need to have their data classes linked via annotations
                WebSocketDataClass webSocketDataClass = wsMessage.getClass().getAnnotation(WebSocketDataClass.class);
                dataClass = webSocketDataClass.value();
            }

            WSData wsData = (WSData) wsMessage.getWebSocketData();

            ArrayList<Field> outgoingDataFields = new ArrayList<>();
            // Data class
            Field[] fields = dataClass.getDeclaredFields();
            for (Field field : fields) {
                for (Annotation annotation : field.getDeclaredAnnotations()) {
                    if (annotation instanceof WSDataOutgoing) {
                        outgoingDataFields.add(field);
                        break;
                    }
                }
            }

            // Super class
            fields = dataClass.getSuperclass().getDeclaredFields();
            for (Field field : fields) {
                for (Annotation annotation : field.getDeclaredAnnotations()) {
                    if (annotation instanceof WSDataOutgoing) {
                        outgoingDataFields.add(field);
                        break;
                    }
                }
            }

            JSONObject jsonObject = new JSONObject();
            for (Field field : outgoingDataFields) {
                String fieldName = field.getName();
                String capFieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

                try {
                    Method fieldMethod = dataClass.getMethod("get" + capFieldName);

                    if (field.isAnnotationPresent(WSDataTypeScriptClass.class)) {
                        Object result = fieldMethod.invoke(wsData);

                        if (result instanceof List) {
                            JSONArray jsonArray = new JSONArray();
                            List resultList = (List) result;

                            for (Object resultListItem : resultList) {
                                if (resultListItem instanceof JSONWeb) {
                                    jsonArray.put(((JSONWeb) resultListItem).prepareForJSON());
                                } else if (resultListItem instanceof String) {
                                    jsonArray.put(resultListItem);
                                }
                            }

                            jsonObject.put("" + fieldName, jsonArray);
                        } else {
                            if (result instanceof JSONWeb) {
                                jsonObject.put("" + fieldName, ((JSONWeb) result).prepareForJSON());
                            } else {
                                jsonObject.put("" + fieldName, fieldMethod.invoke(wsData));
                            }
                        }
                    } else {
                        jsonObject.put("" + fieldName, fieldMethod.invoke(wsData));
                    }
                } catch (NoSuchMethodException ex) {
                    Error.WEBSOCKET_PARSE_METHOD.record().additionalInformation("Variable name is " + capFieldName).create(ex);
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
