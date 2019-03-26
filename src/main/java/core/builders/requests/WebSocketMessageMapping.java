package core.builders.requests;

import org.apache.log4j.Logger;
import org.reflections.Reflections;
import requests.spark.websockets.objects.MessageType;

import java.util.HashMap;
import java.util.Set;

public class WebSocketMessageMapping {
    private static Logger log = Logger.getLogger(WebSocketMessageMapping.class);

    private static HashMap<String, Class> MESSAGE_MAPPINGS = new HashMap<>();

    public static void buildMappings() {
        Set<Class<?>> messageTypes = new Reflections("requests.spark.websockets.objects").getTypesAnnotatedWith(MessageType.class);

        for (Class clazz : messageTypes) {
            MessageType messageType = (MessageType) clazz.getAnnotation(MessageType.class);
            MESSAGE_MAPPINGS.put(messageType.value(), clazz);
            log.info("Mapped ws message " + messageType.value() + " to " + clazz);
        }
    }

    public static Class mappingClass(String messageType) {
        return MESSAGE_MAPPINGS.get(messageType);
    }
}
