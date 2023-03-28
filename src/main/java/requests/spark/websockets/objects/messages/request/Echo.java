package requests.spark.websockets.objects.messages.request;

import log.AppLogger;
import org.apache.logging.log4j.Logger;
import requests.spark.websockets.WebSocketManager;
import requests.spark.websockets.WebSocketSession;
import requests.spark.websockets.objects.*;
import requests.spark.websockets.objects.messages.dataobjects.EchoData;
import requests.spark.websockets.objects.messages.dataobjects.EchoPushData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

@MessageType("Echo")
@WebSocketDataClass(EchoData.class)
public class Echo extends Message {
    private static Logger log = AppLogger.logger();

    public void process() {
        EchoData echoData = (EchoData) this.getWebSocketData();
        echoData.setEchoResponse("This is our response");
        log.info("Echo class being run on the server side");

        WebSocketSession webSocketSession = new WebSocketSession()
                .session(echoData.getSession())
                .type(WebSocketSession.TYPE_PLAYER);
        WebSocketManager.getInstance().addSession(webSocketSession);
        
        // Send off push request to listeners
        Push.message(PushMessage.ECHO_PUSH)
                .data(new EchoPushData("Pushed echo message!"))
                .to(Audience.ALL)
                .push();
    }
}
