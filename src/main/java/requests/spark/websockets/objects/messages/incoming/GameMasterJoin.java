package requests.spark.websockets.objects.messages.incoming;

import game.Game;
import game.GameManager;
import game.actors.GameMaster;
import game.actors.Player;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import requests.spark.websockets.WebSocketManager;
import requests.spark.websockets.WebSocketSession;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;

@MessageType("GameMasterJoin")
public class GameMasterJoin extends Message {
    private static Logger log = Logger.getLogger(GameMasterJoin.class);

    private String localStorageUUID = null;

    public void process() {
        Game currentGame = GameManager.getInstance().getCurrentGame();

        GameMaster gameMaster = null;
        if (localStorageUUID != null) {
            gameMaster = currentGame.findGameMaster(localStorageUUID);
        }
        if (gameMaster == null) {
            gameMaster = currentGame.createGameMaster();
        }

        WebSocketSession webSocketSession = new WebSocketSession()
                .session(getSession())
                .type(WebSocketSession.TYPE_GAME_MASTER);
        WebSocketManager.getInstance().addSession(webSocketSession);

        addResponseData("gameMasterUUID", gameMaster.getUuid());

        // Send information including all the players that are part of the game
        JSONArray allPlayers = new JSONArray();
        for (Player player : currentGame.getPlayers()) {
            JSONObject playerJSON = new JSONObject();

            playerJSON.put("playerUUID", player.getUuid());
            playerJSON.put("playerID", player.getId());
            playerJSON.put("playerName", player.getName());

            allPlayers.put(playerJSON);
        }
        addResponseData("players", allPlayers);

        handleResponse();

        handleResponse();
    }

    public void populate(JSONObject jsonObject) {
        if (jsonObject.has("_localStorageUUID") && !jsonObject.isNull("_localStorageUUID")) {
            localStorageUUID = jsonObject.getString("_localStorageUUID");
        }
    }
}
