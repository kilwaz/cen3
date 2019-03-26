package requests.spark.websockets.objects.messages;

import org.apache.log4j.Logger;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;

@MessageType("GameQuery")
public class GameQuery extends Message {
    private static Logger log = Logger.getLogger(GameQuery.class);
    private String message;

    public void process(Session session) {
        log.info("Our message is " + message);
    }

    public void populate(JSONObject jsonObject) {
        this.message = jsonObject.getString("_message");
    }
}
