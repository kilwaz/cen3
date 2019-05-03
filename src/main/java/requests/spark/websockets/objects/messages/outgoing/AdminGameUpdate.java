package requests.spark.websockets.objects.messages.outgoing;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;

@MessageType("AdminGameUpdate")
public class AdminGameUpdate extends Message {
    private static Logger log = Logger.getLogger(AdminGameUpdate.class);

    public void process() {

        handleResponse();
    }

    public void populate(JSONObject jsonObject) {

    }
}
