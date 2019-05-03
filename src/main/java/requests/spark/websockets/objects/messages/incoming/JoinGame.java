package requests.spark.websockets.objects.messages.incoming;

import game.Game;
import game.GameManager;
import game.actors.Player;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.outgoing.NewPlayerJoined;

@MessageType("JoinGame")
public class JoinGame extends Message {
    private static Logger log = Logger.getLogger(JoinGame.class);

    public void process() {
        // Perform required action
        Game currentGame = GameManager.getInstance().getCurrentGame();
        Player newPlayer = currentGame.createPlayer();

        // Add response values to this response
        addResponseData("playerUUID", newPlayer.getUuid());

        // Send off push request to listeners
        NewPlayerJoined newPlayerJoined = Message.create(NewPlayerJoined.class);
        newPlayerJoined.newPlayer(newPlayer);
        newPlayerJoined.sendTo(Message.ALL_ADMINS);

        // Send response
        handleResponse();
    }

    public void populate(JSONObject jsonObject) {

    }
}
