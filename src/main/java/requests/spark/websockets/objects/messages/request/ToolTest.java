package requests.spark.websockets.objects.messages.request;

import log.AppLogger;
import org.apache.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataobjects.ToolTestData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

@MessageType("ToolTest")
@WebSocketDataClass(ToolTestData.class)
public class ToolTest extends Message {
    private static Logger log = AppLogger.logger();

    public void process() {
        ToolTestData toolTestData = (ToolTestData) this.getWebSocketData();

        log.info("The value was: " + toolTestData.getMessage());

        toolTestData.setMessage("Server says hello");
    }
}

