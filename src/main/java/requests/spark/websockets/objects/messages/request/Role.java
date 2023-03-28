package requests.spark.websockets.objects.messages.request;

import log.AppLogger;
import org.apache.logging.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataobjects.RoleData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

@MessageType("Role")
@WebSocketDataClass(RoleData.class)
public class Role extends Message {
    private static Logger log = AppLogger.logger();

    public void process() {
        RoleData roleData = (RoleData) this.getWebSocketData();

    }
}
