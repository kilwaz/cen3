package requests.spark.websockets.objects.messages.request;

import game.Game;
import game.GameManager;
import game.actors.Admin;
import game.actors.Player;
import log.AppLogger;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import requests.spark.websockets.WebSocketManager;
import requests.spark.websockets.WebSocketSession;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataobjects.AdminGameData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

@MessageType("AdminGame")
@WebSocketDataClass(AdminGameData.class)
public class AdminGame extends Message {
    private static Logger log = AppLogger.logger();

    public void process() {
        AdminGameData adminGameData = (AdminGameData) this.getWebSocketData();
        Game currentGame = GameManager.getInstance().getCurrentGame();

        String localStorageUUID = adminGameData.getLocalStorageUUID();

        Admin admin = null;
        if (localStorageUUID != null) {
            admin = currentGame.findAdmin(localStorageUUID);
        }
        if (admin == null) {
            admin = currentGame.createAdmin();
        }

        WebSocketSession webSocketSession = new WebSocketSession()
                .session(adminGameData.getSession())
                .type(WebSocketSession.TYPE_ADMIN);
        WebSocketManager.getInstance().addSession(webSocketSession);

        adminGameData.setAdminUUID(admin.getUuid());

        // Send information including all the players that are part of the game
        JSONArray allPlayers = new JSONArray();
        for (Player player : currentGame.getPlayers()) {
            allPlayers.put(player.prepareForJSON());
        }

        adminGameData.setAllPlayers(allPlayers);
    }
}
