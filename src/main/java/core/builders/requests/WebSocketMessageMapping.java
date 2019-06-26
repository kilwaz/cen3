package core.builders.requests;

import org.apache.log4j.Logger;
import org.reflections.Reflections;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataobjects.WebSocketDataClass;

import java.util.HashMap;
import java.util.Set;

public class WebSocketMessageMapping {
    private static Logger log = Logger.getLogger(WebSocketMessageMapping.class);

    private static HashMap<String, Class> MESSAGE_MAPPINGS = new HashMap<>();
    private static HashMap<Class, Class> DATA_OBJECT_MAPPING = new HashMap<>();

    public static void buildMappings() {
        // Map websocket process classes to request name
        Set<Class<?>> messageTypes = new Reflections("requests.spark.websockets.objects").getTypesAnnotatedWith(MessageType.class);
        for (Class clazz : messageTypes) {
            MessageType messageType = (MessageType) clazz.getAnnotation(MessageType.class);
            MESSAGE_MAPPINGS.put(messageType.value(), clazz);
            log.info("Mapped ws message " + messageType.value() + " to " + clazz);
        }

        // Map websocket data objects to their respective logic classes
        Set<Class<?>> dataObjects = new Reflections("requests.spark.websockets.objects.messages.dataobjects").getTypesAnnotatedWith(WebSocketDataClass.class);
        for (Class clazz : dataObjects) {
            WebSocketDataClass webSocketDataClass = (WebSocketDataClass) clazz.getAnnotation(WebSocketDataClass.class);
            DATA_OBJECT_MAPPING.put(webSocketDataClass.value(), clazz);
            log.info("Mapped ws data object " + webSocketDataClass.value() + " to " + clazz);
        }
    }

    public static Class mappingClass(String messageType) {
        return MESSAGE_MAPPINGS.get(messageType);
    }

    public static Class dataClass(Class processClass) {
        return DATA_OBJECT_MAPPING.get(processClass);
    }
}
