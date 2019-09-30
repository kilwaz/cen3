package requests.spark.websockets.objects;

import error.Error;
import log.AppLogger;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import requests.spark.websockets.objects.messages.mapping.WSData;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;

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
                        jsonObject.put(field.getName(), fieldMethod.invoke(this));
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
