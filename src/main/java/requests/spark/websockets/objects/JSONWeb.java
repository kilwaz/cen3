package requests.spark.websockets.objects;

import error.Error;
import log.AppLogger;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import requests.spark.websockets.objects.messages.mapping.WSData;
import requests.spark.websockets.objects.messages.mapping.WSDataJSONArrayClass;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;
import requests.spark.websockets.objects.messages.mapping.WSDataTypeScriptClass;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JSONWeb {
    private static Logger log = AppLogger.logger();

    public JSONWeb() {

    }

    public void populateFromJSON(JSONObject jsonObject) {
        Field[] dataFields = this.getClass().getDeclaredFields();

        for (Field field : dataFields) {
            if (field.isAnnotationPresent(WSDataReference.class)) {
                String fieldName = field.getName();
                String capFieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

                try {
                    if (field.isAnnotationPresent(WSDataJSONArrayClass.class)) {
                        if (jsonObject.has("_" + fieldName)) {
                            Method fieldMethod = this.getClass().getMethod("set" + capFieldName, field.getType());
                            List newObjectList = new ArrayList<>();

                            JSONArray jsonArray = jsonObject.getJSONArray("_" + fieldName);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONWeb newJsonWebListObject = (JSONWeb) field.getAnnotation(WSDataTypeScriptClass.class).value().getConstructor().newInstance();
                                newJsonWebListObject.populateFromJSON(jsonArray.getJSONObject(i));
                                newObjectList.add(newJsonWebListObject);
                            }

                            fieldMethod.invoke(this, newObjectList);
                        } else {
                            log.info("Expected incoming JSON field of " + "_" + fieldName + " has not been found on the object");
                        }
                    } else if (field.isAnnotationPresent(WSDataTypeScriptClass.class)) {
                        if (jsonObject.has("_" + fieldName)) {
                            Method fieldMethod = this.getClass().getMethod("set" + capFieldName, field.getAnnotation(WSDataTypeScriptClass.class).value());
                            fieldMethod.invoke(this, "");
                        } else {
                            log.info("Expected incoming JSON field of " + "_" + fieldName + " has not been found on the object");
                        }
                    } else if (field.getType() == Integer.class) { // Integer
                        if (jsonObject.has("_" + fieldName)) {
                            Method fieldMethod = this.getClass().getMethod("set" + capFieldName, Integer.class);
                            fieldMethod.invoke(this, jsonObject.getInt("_" + fieldName));
                        } else {
                            log.info("Expected incoming JSON field of " + "_" + fieldName + " has not been found on the object");
                        }
                    } else if (field.getType() == String.class) { // String
                        if (jsonObject.has("_" + fieldName)) {
                            Method fieldMethod = this.getClass().getMethod("set" + capFieldName, String.class);
                            fieldMethod.invoke(this, jsonObject.getString("_" + fieldName));
                        }
                    }
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException |
                         InstantiationException ex) {
                    Error.WEBSOCKET_JSON_RESPONSE.record()
                            .additionalInformation("Field name: " + capFieldName)
                            .additionalInformation("Method name: get" + capFieldName)
                            .create(ex);
                }
            }
        }
    }

    public JSONObject prepareForJSON(WSData... requestedData) {
        List<WSData> requestedDataList = new ArrayList<>(Arrays.asList(requestedData));

        JSONObject jsonObject = new JSONObject();
        Field[] dataFields = this.getClass().getDeclaredFields();

        for (Field field : dataFields) {
            if (field.isAnnotationPresent(WSDataReference.class)) {
                String fieldName = field.getName();
                String capFieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

                try {
                    Method fieldMethod = this.getClass().getMethod("get" + capFieldName);

                    if (requestedDataList.size() == 0 || requestedDataList.contains(field.getAnnotation(WSDataReference.class).value())) {
                        Object valueObject = fieldMethod.invoke(this);

                        if (valueObject instanceof List) {
                            JSONArray jsonArray = new JSONArray();
                            List resultList = (List) valueObject;

                            for (Object resultListItem : resultList) {
                                jsonArray.put(((JSONWeb) resultListItem).prepareForJSON());
                            }

                            jsonObject.put(field.getName(), jsonArray);
                        } else {
                            if (valueObject instanceof JSONWeb) {
                                JSONWeb jsonWebObject = (JSONWeb) valueObject;
                                jsonObject.put(field.getName(), jsonWebObject.prepareForJSON());
                            } else {
                                jsonObject.put(field.getName(), fieldMethod.invoke(this));
                            }
                        }
                    }
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
                    Error.WEBSOCKET_JSON_RESPONSE.record()
                            .additionalInformation("Field name: " + capFieldName)
                            .additionalInformation("Method name: get" + capFieldName)
                            .create(ex);
                }
            }
        }

        return jsonObject;
    }
}
