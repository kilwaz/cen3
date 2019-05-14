package requests.spark.websockets.objects.messages.incoming;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;

@MessageType("AllPlayers")
public class AllPlayers extends Message {
    private static Logger log = Logger.getLogger(AllPlayers.class);
    private String message;

    public void process() {
        log.info("Our message is " + message);
    }

    public void populate(JSONObject jsonObject) {
        this.message = jsonObject.getString("_message");
    }
}
