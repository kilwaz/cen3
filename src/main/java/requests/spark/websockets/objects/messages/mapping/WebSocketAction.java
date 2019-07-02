package requests.spark.websockets.objects.messages.mapping;

import core.builders.requests.WebSocketMessageMapping;
import data.model.objects.json.JSONContainer;
import org.json.JSONObject;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.messages.dataobjects.WebSocketData;

import java.lang.reflect.InvocationTargetException;

public class WebSocketAction<WSMessage extends Message, WSAPI extends WebSocketAPI, WSData extends WebSocketData> {
    public WSMessage incoming(JSONContainer jsonContainerDecoder) {
        try {
            JSONObject jsonObjectDecoded = jsonContainerDecoder.toJSONObject();

            Class messageClass = WebSocketMessageMapping.messageClass(jsonObjectDecoded.getString("_type"));
            Class dataClass = WebSocketMessageMapping.dataClass(messageClass);
            Class mappingClass = WebSocketMessageMapping.mappingClass(dataClass);

            WSMessage wsMessage = (WSMessage) messageClass.getConstructor().newInstance();
            WSAPI wsAPI = (WSAPI) dataClass.getConstructor().newInstance();
            WSData wsData = (WSData) mappingClass.getConstructor().newInstance();

            for (String key : jsonObjectDecoded.keySet()) {
                wsAPI.getDataItemInModel(key).getObjectSetMethod().invoke(jsonObjectDecoded.get(key));
            }

            return wsMessage;
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public JSONContainer outgoing(WSMessage wsMessage) {
        try {
            Class dataClass = WebSocketMessageMapping.dataClass(wsMessage.getClass());

            WSAPI wsAPI = (WSAPI) dataClass.getConstructor().newInstance();

            JSONObject jsonObject = new JSONObject();
            for (String varKey : wsAPI.getDataItemOutModels().keySet()) {
                DataItemModel dataItemModel = wsAPI.getDataItemOutModel(varKey);
                jsonObject.put(varKey, dataItemModel.getObjectGetMethod().invoke(wsMessage));
            }

            return new JSONContainer(jsonObject);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException ex) {
            ex.printStackTrace();
        }

        return new JSONContainer();
    }
}
