package requests.spark.websockets.objects.messages.request;

import log.AppLogger;
import org.apache.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataobjects.AllPlayersData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

@MessageType("AllPlayers")
@WebSocketDataClass(AllPlayersData.class)
public class AllPlayers extends Message {
    private static Logger log = AppLogger.logger();

    public void process() {
        AllPlayersData allPlayersData = (AllPlayersData) this.getWebSocketData();
        log.info("Our message is " + allPlayersData.getMessage());
    }
}
