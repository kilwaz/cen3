package requests.spark.websockets.objects.messages;

import game.Game;
import game.GameManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;

@MessageType("AdminGameInfo")
public class AdminGameInfo extends Message {
    private static Logger log = Logger.getLogger(AdminGameInfo.class);

    public void process() {
        Game currentGame = GameManager.getInstance().getCurrentGame();

        handleResponse();
    }

    public void populate(JSONObject jsonObject) {

    }
}
