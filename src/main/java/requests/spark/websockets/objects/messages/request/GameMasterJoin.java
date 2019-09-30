package requests.spark.websockets.objects.messages.request;

import game.Game;
import game.GameManager;
import game.actors.GameMaster;
import game.actors.Player;
import log.AppLogger;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import requests.spark.websockets.WebSocketManager;
import requests.spark.websockets.WebSocketSession;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataobjects.GameMasterJoinData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

@MessageType("GameMasterJoin")
@WebSocketDataClass(GameMasterJoinData.class)
public class GameMasterJoin extends Message {
    private static Logger log = AppLogger.logger();

    public void process() {
        GameMasterJoinData gameMasterJoinData = (GameMasterJoinData) this.getWebSocketData();
        Game currentGame = GameManager.getInstance().getCurrentGame();

        GameMaster gameMaster = null;
        if (gameMasterJoinData.getLocalStorageUUID() != null && !gameMasterJoinData.getLocalStorageUUID().isEmpty()) {
            gameMaster = currentGame.findGameMaster(gameMasterJoinData.getLocalStorageUUID());
        }
        if (gameMaster == null) {
            gameMaster = currentGame.createGameMaster();
        }

        WebSocketSession webSocketSession = new WebSocketSession()
                .session(gameMasterJoinData.getSession())
                .type(WebSocketSession.TYPE_GAME_MASTER);
        WebSocketManager.getInstance().addSession(webSocketSession);

        gameMasterJoinData.setGameMasterUUID(gameMaster.getUuid());

        // Send information including all the players that are part of the game
        JSONArray allPlayers = new JSONArray();
        for (Player player : currentGame.getPlayers()) {
            allPlayers.put(player.prepareForJSON());
        }

        gameMasterJoinData.setPlayers(allPlayers);
    }
}
