package requests.spark.websockets.objects.messages;

import game.Game;
import game.GameManager;
import game.actors.Player;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;

@MessageType("JoinGame")
public class JoinGame extends Message {
    private static Logger log = Logger.getLogger(JoinGame.class);

    public void process() {
        // Perform required action
        Game currentGame = GameManager.getInstance().getCurrentGame();
        Player newPlayer = currentGame.createPlayer();

        // Add response values
        addResponseData("playerUUID", newPlayer.getUuid());

        log.info("Created a new player for the game");

        NewPlayerJoined newPlayerJoined = Message.create(NewPlayerJoined.class);
        newPlayerJoined.newPlayer(newPlayer);
        newPlayerJoined.sendTo(Message.ALL_ADMINS);

        // Send response
        handleResponse();
    }

    public void populate(JSONObject jsonObject) {

    }
}
