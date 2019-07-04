package requests.spark.websockets.objects.messages.request;

import game.Game;
import game.GameManager;
import game.actors.GameMaster;
import game.actors.Player;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import requests.spark.websockets.WebSocketManager;
import requests.spark.websockets.WebSocketSession;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataobjects.GameMasterData;

@MessageType("GameMasterJoin")
public class GameMasterJoin extends Message {
    private static Logger log = Logger.getLogger(GameMasterJoin.class);

    public void process() {
        GameMasterData gameMasterData = (GameMasterData) this.getWebSocketData();
        Game currentGame = GameManager.getInstance().getCurrentGame();

        GameMaster gameMaster = null;
        if (gameMasterData.getLocalStorageUUID() != null) {
            gameMaster = currentGame.findGameMaster(gameMasterData.getLocalStorageUUID());
        }
        if (gameMaster == null) {
            gameMaster = currentGame.createGameMaster();
        }

        WebSocketSession webSocketSession = new WebSocketSession()
                .session(gameMasterData.getSession())
                .type(WebSocketSession.TYPE_GAME_MASTER);
        WebSocketManager.getInstance().addSession(webSocketSession);

        gameMasterData.setGameMasterUUID(gameMaster.getUuid());

        // Send information including all the players that are part of the game
        JSONArray allPlayers = new JSONArray();
        for (Player player : currentGame.getPlayers()) {
            allPlayers.put(player.prepareForJSON());
        }

        gameMasterData.setAllPlayers(allPlayers);
    }
}
