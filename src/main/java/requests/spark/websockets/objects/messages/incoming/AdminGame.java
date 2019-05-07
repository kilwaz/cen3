package requests.spark.websockets.objects.messages.incoming;

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

    private String localStorageUUID = null;

    public void process() {
        Game currentGame = GameManager.getInstance().getCurrentGame();

        Admin admin = null;
        if (localStorageUUID != null) {
            admin = currentGame.findAdmin(localStorageUUID);
        }
        if (admin == null) {
            admin = currentGame.createAdmin();
        }

        WebSocketSession webSocketSession = new WebSocketSession()
                .session(getSession())
                .type(WebSocketSession.TYPE_ADMIN);
        WebSocketManager.getInstance().addSession(webSocketSession);

        addResponseData("adminUUID", admin.getUuid());

        handleResponse();
    }

    public void populate(JSONObject jsonObject) {
        if (jsonObject.has("_localStorageUUID") && !jsonObject.isNull("_localStorageUUID")) {
            localStorageUUID = jsonObject.getString("_localStorageUUID");
        }
    }
}
