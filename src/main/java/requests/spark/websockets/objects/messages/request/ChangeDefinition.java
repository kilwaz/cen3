package requests.spark.websockets.objects.messages.request;

import log.AppLogger;
import org.apache.logging.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataobjects.ChangeDefinitionData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

@MessageType("ChangeDefinition")
@WebSocketDataClass(ChangeDefinitionData.class)
public class ChangeDefinition extends Message {
    private static Logger log = AppLogger.logger();

    public void process() {
        ChangeDefinitionData changeDefinitionData = (ChangeDefinitionData) this.getWebSocketData();

        // Handle it!
        log.info(changeDefinitionData.getDefinition().getExpression());
    }
}
