package requests.spark.websockets.objects.messages.request;

import game.Game;
import game.GameManager;
import game.actors.Player;
import log.AppLogger;
import org.apache.log4j.Logger;
import requests.spark.websockets.objects.*;
import requests.spark.websockets.objects.messages.dataobjects.PlayerNameChangeData;
import requests.spark.websockets.objects.messages.dataobjects.PlayerNameUpdateData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

// Triggered when a player changes there name
@MessageType("PlayerNameChange")
@WebSocketDataClass(PlayerNameChangeData.class)
public class PlayerNameChange extends Message {
    private static Logger log = AppLogger.logger();

    public void process() {
        PlayerNameChangeData playerNameChangeData = (PlayerNameChangeData) this.getWebSocketData();
        Game currentGame = GameManager.getInstance().getCurrentGame();

        if (!playerNameChangeData.getPlayerUUID().isEmpty()) {
            Player player = currentGame.findPlayer(playerNameChangeData.getPlayerUUID());

            if (!playerNameChangeData.getPlayerName().isEmpty()) {
                player.setName(playerNameChangeData.getPlayerName());

                // Send off push request to listeners
                Push.message(PushMessage.PLAYER_NAME_UPDATE)
                        .data(new PlayerNameUpdateData(player))
                        .to(Audience.ALL_ADMINS, Audience.ALL_GAME_MASTERS)
                        .push();
            }
        }
    }
}
