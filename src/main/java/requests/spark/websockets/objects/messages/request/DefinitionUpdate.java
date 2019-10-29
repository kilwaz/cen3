package requests.spark.websockets.objects.messages.request;

import log.AppLogger;
import org.apache.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataobjects.DefinitionUpdateData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

@MessageType("DefinitionUpdate")
@WebSocketDataClass(DefinitionUpdateData.class)
public class DefinitionUpdate extends Message {
    private static Logger log = AppLogger.logger();

    public void process() {
        DefinitionUpdateData definitionUpdateData = (DefinitionUpdateData) this.getWebSocketData();
        String definitionName = definitionUpdateData.getDefinitionName();
        log.info("Def name is " + definitionName);

//        WebSocketSession webSocketSession = new WebSocketSession()
//                .session(echoData.getSession())
//                .type(WebSocketSession.TYPE_PLAYER);
//        WebSocketManager.getInstance().addSession(webSocketSession);

        // Send off push request to listeners
//        Push.message(PushMessage.ECHO_PUSH)
//                .data(new EchoPushData("Pushed echo message!"))
//                .to(Audience.ALL)
//                .push();
    }
}
