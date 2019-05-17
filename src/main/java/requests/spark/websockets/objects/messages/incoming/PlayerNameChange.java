package requests.spark.websockets.objects.messages.incoming;

import game.Game;
import game.GameManager;
import game.actors.Player;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.outgoing.NewPlayerJoined;
import requests.spark.websockets.objects.messages.outgoing.PlayerNameUpdate;

// Triggered when a player changes there name
@MessageType("PlayerNameChange")
public class PlayerNameChange extends Message {
    private static Logger log = Logger.getLogger(PlayerNameChange.class);

    private String playerUUID = "";
    private String playerName = "";

    public void process() {
        Game currentGame = GameManager.getInstance().getCurrentGame();

        if (!playerUUID.isEmpty()) {
            Player player = currentGame.findPlayer(playerUUID);

            if (!playerName.isEmpty()) {
                player.setName(playerName);

                // Send off push request to listeners
                PlayerNameUpdate playerNameUpdate = Message.create(PlayerNameUpdate.class);
                playerNameUpdate.player(player);
                playerNameUpdate.sendTo(Message.ALL_ADMINS);
            }
        }

        handleResponse();
    }

    public void prepareToSend() {

    }

    public void populate(JSONObject jsonObject) {
        if (jsonObject.has("_playerName") && !jsonObject.isNull("_playerName")) {
            playerName = jsonObject.getString("_playerName");
        }
        if (jsonObject.has("_playerUUID") && !jsonObject.isNull("_playerUUID")) {
            playerUUID = jsonObject.getString("_playerUUID");
        }
    }
}
