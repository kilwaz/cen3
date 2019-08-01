package requests.spark.websockets.objects.messages.request;

import log.AppLogger;
import org.apache.log4j.Logger;
import requests.spark.websockets.objects.*;
import requests.spark.websockets.objects.messages.dataobjects.ClearGameScreenData;
import requests.spark.websockets.objects.messages.dataobjects.ClearScreenData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

@MessageType("ClearScreen")
@WebSocketDataClass(ClearScreenData.class)
public class ClearScreen extends Message {
    private static Logger log = AppLogger.logger();

    public void process() {
        Push.message(PushMessage.CLEAR_GAME_SCREEN)
                .data(new ClearGameScreenData())
                .to(Audience.ALL_ADMINS)
                .push();

        log.info("Clearing screen");
    }
}
