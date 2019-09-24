package requests.spark.websockets.objects;

import requests.spark.websockets.WebSocketManager;
import requests.spark.websockets.WebSocketSession;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Audience {
    public static final int ALL_PLAYERS = 1;
    public static final int ALL_ADMINS = 2;
    public static final int ALL_GAME_MASTERS = 3;
    public static final int ALL = 4;

    private ArrayList audiences = new ArrayList();

    public Audience(Integer... audiences) {
        this.audiences.addAll(Arrays.asList(audiences));
    }

    public List<WebSocketSession> audience() {
        List<WebSocketSession> audienceList = new ArrayList<>();
        if (audiences.contains(Audience.ALL_ADMINS)) {
            audienceList.addAll(WebSocketManager.getInstance().getAdmins());
        }
        if (audiences.contains(ALL_PLAYERS)) {
            audienceList.addAll(WebSocketManager.getInstance().getPlayers());
        }
        if (audiences.contains(ALL_GAME_MASTERS)) {
            audienceList.addAll(WebSocketManager.getInstance().getGameMasters());
        }
        if (audiences.contains(ALL)) {
            audienceList.addAll(WebSocketManager.getInstance().getAllSessions());
        }

        return audienceList;
    }
}
