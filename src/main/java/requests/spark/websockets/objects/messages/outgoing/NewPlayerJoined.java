package requests.spark.websockets.objects.messages.outgoing;

import game.actors.Player;
import org.apache.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;

@MessageType("NewPlayerJoined")
public class NewPlayerJoined extends Message {
    private static Logger log = Logger.getLogger(NewPlayerJoined.class);

    private Player newPlayer = null;

    public NewPlayerJoined newPlayer(Player newPlayer) {
        this.newPlayer = newPlayer;
        return this;
    }

    public void prepareToSend() {
        addResponseData("newPlayerUUID", newPlayer.getUuid());
        addResponseData("newPlayerID", newPlayer.getId());
    }
}
