package requests.spark.websockets.objects.messages.incoming;

import game.Game;
import game.GameManager;
import game.actors.Admin;
import game.actors.Player;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import requests.spark.websockets.WebSocketManager;
import requests.spark.websockets.WebSocketSession;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;

import java.util.UUID;

@MessageType("AdminGame")
public class AdminGame extends Message {
    private static Logger log = Logger.getLogger(AdminGame.class);

    // Incoming
    private String localStorageUUID = null;

    // Outgoing
    private UUID adminUUID = null;
    private JSONArray allPlayers = null;

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

        adminUUID = admin.getUuid();
        addResponseData("adminUUID", admin.getUuid());

        // Send information including all the players that are part of the game
        allPlayers = new JSONArray();
        for (Player player : currentGame.getPlayers()) {
            JSONObject playerJSON = new JSONObject();

            playerJSON.put("playerUUID", player.getUuid());
            playerJSON.put("playerID", player.getId());
            playerJSON.put("playerName", player.getName());

            allPlayers.put(playerJSON);
        }
        addResponseData("players", allPlayers);

        handleResponse();
    }

    public void populate(JSONObject jsonObject) {
        localStorageUUID = extractJSONString(jsonObject, "_localStorageUUID");
    }
}
