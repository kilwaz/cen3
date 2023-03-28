package core.builders.requests;

import log.AppLogger;
import org.apache.logging.log4j.Logger;
import org.reflections.Reflections;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;
import requests.spark.websockets.objects.messages.mapping.WebSocketLinkClass;

import java.util.HashMap;
import java.util.Set;

public class WebSocketMessageMapping {
    private static Logger log = AppLogger.logger();

    private static HashMap<String, Class> MESSAGE_MAPPINGS = new HashMap<>();
    private static HashMap<Class, Class> DATA_OBJECT_MAPPING = new HashMap<>();
    private static HashMap<Class, Class> MAPPING = new HashMap<>();

    public static void buildMappings() {
        // Map websocket process classes to request name
        Set<Class<?>> messageTypes = new Reflections("requests.spark.websockets.objects").getTypesAnnotatedWith(MessageType.class);
        for (Class clazz : messageTypes) {
            MessageType messageType = (MessageType) clazz.getAnnotation(MessageType.class);
            MESSAGE_MAPPINGS.put(messageType.value(), clazz);
            //log.info("Mapped ws message " + messageType.value() + " to " + clazz);
        }

        //TODO: Code not used below?
        // Map websocket data objects to their respective logic classes
        Set<Class<?>> dataObjects = new Reflections("requests.spark.websockets.objects.messages.dataobjects").getTypesAnnotatedWith(WebSocketDataClass.class);
        for (Class clazz : dataObjects) {
            WebSocketDataClass webSocketDataClass = (WebSocketDataClass) clazz.getAnnotation(WebSocketDataClass.class);
            DATA_OBJECT_MAPPING.put(webSocketDataClass.value(), clazz);
            log.info("Mapped ws data object " + webSocketDataClass.value() + " to " + clazz);
        }

        //TODO: Code not used below?
        // Map websocket data objects to their respective logic classes
        Set<Class<?>> mappingObjects = new Reflections("requests.spark.websockets.objects.messages.mapping").getTypesAnnotatedWith(WebSocketLinkClass.class);
        for (Class clazz : mappingObjects) {
            WebSocketLinkClass webSocketLinkClass = (WebSocketLinkClass) clazz.getAnnotation(WebSocketLinkClass.class);
            MAPPING.put(webSocketLinkClass.linkClass(), clazz);
            log.info("Mapped ws mapping object " + webSocketLinkClass.linkClass() + " to " + clazz);
        }
    }

    public static Class messageClass(String messageType) {
        return MESSAGE_MAPPINGS.get(messageType);
    }

    public static Class dataClass(Class processClass) {
        return DATA_OBJECT_MAPPING.get(processClass);
    }

    public static Class mappingClass(Class processClass) {
        return MAPPING.get(processClass);
    }
}
