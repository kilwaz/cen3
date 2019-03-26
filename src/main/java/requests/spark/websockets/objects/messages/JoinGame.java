package requests.spark.websockets.objects.messages;

import data.model.objects.json.JSONContainer;
import game.Game;
import game.GameManager;
import game.Player;
import org.apache.log4j.Logger;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;

import java.io.IOException;

@MessageType("JoinGame")
public class JoinGame extends Message {
    private static Logger log = Logger.getLogger(JoinGame.class);

    public void process(Session session) throws IOException {
        Game currentGame = GameManager.getInstance().getCurrentGame();
        Player newPlayer = currentGame.createPlayer();

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("playerUUID", newPlayer.getUuid());
        jsonResponse.put("type", "JoinedGame");
        jsonResponse.put("callBackUUID", this.getCallBackUUID());

        JSONContainer jsonContainer = new JSONContainer(jsonResponse);

        log.info("Created a new player for the game");

        session.getRemote().sendString(jsonContainer.writeResponse());
    }

    public void populate(JSONObject jsonObject) {

    }
}
