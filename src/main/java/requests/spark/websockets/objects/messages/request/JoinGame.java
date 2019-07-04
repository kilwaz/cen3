package requests.spark.websockets.objects.messages.request;

import game.Game;
import game.GameManager;
import game.actors.Player;
import org.apache.log4j.Logger;
import requests.spark.websockets.WebSocketManager;
import requests.spark.websockets.WebSocketSession;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.WebSocketAction;
import requests.spark.websockets.objects.messages.dataobjects.AnswerUpdateData;
import requests.spark.websockets.objects.messages.dataobjects.JoinGameData;
import requests.spark.websockets.objects.messages.dataobjects.NewPlayerJoinedData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;
import requests.spark.websockets.objects.messages.push.AnswerUpdate;
import requests.spark.websockets.objects.messages.push.NewPlayerJoined;

@MessageType("JoinGame")
@WebSocketDataClass(JoinGameData.class)
public class JoinGame extends Message {
    private static Logger log = Logger.getLogger(JoinGame.class);

    public void process() {
        JoinGameData joinGameData = (JoinGameData) this.getWebSocketData();

        // Perform required action
        Game currentGame = GameManager.getInstance().getCurrentGame();

        Player player = null;
        if (joinGameData.getLocalStorageUUID() != null) {
            player = currentGame.findPlayer(joinGameData.getLocalStorageUUID());
        }

        if (player == null) {
            player = currentGame.createPlayer();
        }

        // Send off push request to listeners
        Message.push(NewPlayerJoined.class, new NewPlayerJoinedData(player), WebSocketAction.ALL_ADMINS);

        // Register the player session within the server
        WebSocketSession webSocketSession = new WebSocketSession()
                .session(joinGameData.getSession())
                .type(WebSocketSession.TYPE_PLAYER);
        WebSocketManager.getInstance().addSession(webSocketSession);

        // Add response values to this response
        joinGameData.setPlayerID(player.getId());
        joinGameData.setPlayerUUID(player.getUuid());
        joinGameData.setPlayerName(player.getName());
    }
}
