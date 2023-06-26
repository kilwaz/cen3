package requests.spark.websockets.objects.messages.request;

import log.AppLogger;
import org.apache.logging.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataobjects.ManagementData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

@MessageType("Management")
@WebSocketDataClass(ManagementData.class)
public class Management extends Message {
    private static Logger log = AppLogger.logger();

    public void process() {
        ManagementData managementData = (ManagementData) this.getWebSocketData();

        Runtime runtime = Runtime.getRuntime();

        long totalMemory = runtime.totalMemory(); // Total memory currently in use by the JVM
        long freeMemory = runtime.freeMemory(); // Out of the total memory, the amount that is currently unused
        long maxMemory = runtime.maxMemory(); // Maximum amount of memory that the JVM will attempt to use

        managementData.setTotalMemory(0.0 + totalMemory / (1024 * 1024));
        managementData.setFreeMemory(0.0 + freeMemory / (1024 * 1024));
        managementData.setMaxMemory(0.0 + maxMemory / (1024 * 1024));
    }
}
