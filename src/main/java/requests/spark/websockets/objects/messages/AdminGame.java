package requests.spark.websockets.objects.messages;

import game.actors.Admin;
import game.Game;
import game.GameManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;

@MessageType("AdminGame")
public class AdminGame extends Message {
    private static Logger log = Logger.getLogger(AdminGame.class);

    public void process() {
        Game currentGame = GameManager.getInstance().getCurrentGame();
        Admin newAdmin = currentGame.createAdmin();

        addResponseData("adminUUID", newAdmin.getUuid());

        log.info("Created a new admin for the game");

        handleResponse();
    }

    public void populate(JSONObject jsonObject) {

    }
}
