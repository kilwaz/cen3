package requests.spark.websockets.objects.messages.outgoing;

import game.actors.Player;
import org.apache.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;


// This is sent by the server to the players with details of the next question
@MessageType("PlayerNameUpdate")
public class PlayerNameUpdate extends Message {
    private static Logger log = Logger.getLogger(PlayerNameUpdate.class);

    private Player player = null;

    public PlayerNameUpdate player(Player player) {
        this.player = player;
        return this;
    }

    public void prepareToSend() {
        addResponseData("playerUUID", player.getUuid());
        addResponseData("newName", player.getName());
    }
}
