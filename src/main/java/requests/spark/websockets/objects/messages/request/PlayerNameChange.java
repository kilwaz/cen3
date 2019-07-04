package requests.spark.websockets.objects.messages.request;

import game.Game;
import game.GameManager;
import game.actors.Player;
import org.apache.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.WebSocketAction;
import requests.spark.websockets.objects.messages.dataobjects.PlayerNameChangeData;
import requests.spark.websockets.objects.messages.dataobjects.PlayerNameUpdateData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;
import requests.spark.websockets.objects.messages.push.PlayerNameUpdate;

// Triggered when a player changes there name
@MessageType("PlayerNameChange")
@WebSocketDataClass(PlayerNameChangeData.class)
public class PlayerNameChange extends Message {
    private static Logger log = Logger.getLogger(PlayerNameChange.class);

    public void process() {
        PlayerNameChangeData playerNameChangeData = (PlayerNameChangeData) this.getWebSocketData();
        Game currentGame = GameManager.getInstance().getCurrentGame();

        if (!playerNameChangeData.getPlayerUUID().isEmpty()) {
            Player player = currentGame.findPlayer(playerNameChangeData.getPlayerUUID());

            if (!playerNameChangeData.getPlayerName().isEmpty()) {
                player.setName(playerNameChangeData.getPlayerName());

                // Send off push request to listeners
                Message.push(PlayerNameUpdate.class, new PlayerNameUpdateData(player), WebSocketAction.ALL_ADMINS);
                Message.push(PlayerNameUpdate.class, new PlayerNameUpdateData(player), WebSocketAction.ALL_GAME_MASTERS);
            }
        }
    }
}
