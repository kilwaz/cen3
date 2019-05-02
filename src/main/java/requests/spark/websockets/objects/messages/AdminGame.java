package requests.spark.websockets.objects.messages;

import game.Game;
import game.GameManager;
import game.actors.Admin;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import requests.spark.websockets.WebSocketManager;
import requests.spark.websockets.WebSocketSession;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;

@MessageType("AdminGame")
public class AdminGame extends Message {
    private static Logger log = Logger.getLogger(AdminGame.class);

    public void process() {
        Game currentGame = GameManager.getInstance().getCurrentGame();
        Admin newAdmin = currentGame.createAdmin();

        WebSocketSession webSocketSession = new WebSocketSession()
                .session(getSession())
                .type(WebSocketSession.TYPE_ADMIN);
        WebSocketManager.getInstance().addSession(webSocketSession);

        addResponseData("adminUUID", newAdmin.getUuid());

        log.info("Created a new admin for the game");

        handleResponse();
    }

    public void populate(JSONObject jsonObject) {

    }
}
