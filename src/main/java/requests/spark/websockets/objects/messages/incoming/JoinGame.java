package requests.spark.websockets.objects.messages.incoming;

import game.Game;
import game.GameManager;
import game.actors.Player;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import requests.spark.websockets.WebSocketManager;
import requests.spark.websockets.WebSocketSession;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.outgoing.NewPlayerJoined;

@MessageType("JoinGame")
public class JoinGame extends Message {
    private static Logger log = Logger.getLogger(JoinGame.class);

    private String localStorageUUID = null;

    public void process() {
        // Perform required action
        Game currentGame = GameManager.getInstance().getCurrentGame();

        Player player = null;
        if (localStorageUUID != null) {
            player = currentGame.findPlayer(localStorageUUID);
        }

        if (player == null) {
            player = currentGame.createPlayer();
        }

        // Send off push request to listeners
        NewPlayerJoined newPlayerJoined = Message.create(NewPlayerJoined.class);
        newPlayerJoined.newPlayer(player);
        newPlayerJoined.sendTo(Message.ALL_ADMINS);

        // Register the player session within the server
        WebSocketSession webSocketSession = new WebSocketSession()
                .session(getSession())
                .type(WebSocketSession.TYPE_PLAYER);
        WebSocketManager.getInstance().addSession(webSocketSession);

        // Add response values to this response
        addResponseData("playerUUID", player.getUuid());
        addResponseData("playerID", player.getId());

        // Send response
        handleResponse();
    }

    public void populate(JSONObject jsonObject) {
        if (jsonObject.has("_localStorageUUID") && !jsonObject.isNull("_localStorageUUID")) {
            localStorageUUID = jsonObject.getString("_localStorageUUID");
        }
    }
}
