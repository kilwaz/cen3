package requests.spark.websockets.objects;

import org.apache.log4j.Logger;
import org.json.JSONObject;

@MessageType("ChatMessage")
public class ChatMessage extends Message {
    private static Logger log = Logger.getLogger(ChatMessage.class);
    private String message;

    public void process() {
        log.info("Our message is " + message);
    }

    public void populate(JSONObject jsonObject) {
        this.message = jsonObject.getString("message");
    }
}
