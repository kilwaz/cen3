package requests.spark.websockets;

import log.AppLogger;
import org.apache.log4j.Logger;
import org.eclipse.jetty.websocket.api.Session;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.TriggerBuilder;
import requests.spark.websockets.objects.Audience;
import requests.spark.websockets.objects.Push;
import requests.spark.websockets.objects.PushMessage;
import requests.spark.websockets.objects.messages.dataobjects.HeartBeatData;
import utils.managers.JobManager;
import utils.timers.WebSocketHeartbeatJob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WebSocketManager {
    private static WebSocketManager instance;
    private static Logger log = AppLogger.logger();

    private List<WebSocketSession> admins = new ArrayList<>();
    private List<WebSocketSession> players = new ArrayList<>();
    private List<WebSocketSession> gameMasters = new ArrayList<>();
    private List<WebSocketSession> allSessions = new ArrayList<>();
    private HashMap<Session, WebSocketSession> sessionQuickReference = new HashMap<>();

    private WebSocketManager() {
        instance = this;

        setupHeartbeatJob();
    }

    private void setupHeartbeatJob() {
        JobDetail websocketHeartbeatJob = JobBuilder.newJob(WebSocketHeartbeatJob.class).build();

        SimpleScheduleBuilder websocketHeartbeatSimpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule();
        TriggerBuilder websocketHeartbeatTriggerBuilder = TriggerBuilder.newTrigger();
        websocketHeartbeatSimpleScheduleBuilder.repeatForever().withIntervalInMilliseconds(5000);

        JobManager.getInstance().scheduleJob(websocketHeartbeatJob, websocketHeartbeatTriggerBuilder.withSchedule(websocketHeartbeatSimpleScheduleBuilder).build());
        websocketHeartbeatTriggerBuilder.startNow();
    }

    public static WebSocketManager getInstance() {
        if (instance == null) {
            instance = new WebSocketManager();
        }
        return instance;
    }

    public void addSession(WebSocketSession webSocketSession) {
        allSessions.add(webSocketSession);
        sessionQuickReference.put(webSocketSession.getSession(), webSocketSession);

        if (webSocketSession.getType() == WebSocketSession.TYPE_ADMIN) {
            admins.add(webSocketSession);
        } else if (webSocketSession.getType() == WebSocketSession.TYPE_PLAYER) {
            players.add(webSocketSession);
        } else if (webSocketSession.getType() == WebSocketSession.TYPE_GAME_MASTER) {
            gameMasters.add(webSocketSession);
        }
    }

    public void removeSession(Session session) {
        WebSocketSession webSocketSession = sessionQuickReference.get(session);

        if (webSocketSession != null) {
            admins.remove(webSocketSession);
            players.remove(webSocketSession);
            allSessions.remove(webSocketSession);
            sessionQuickReference.remove(session);
        }
    }

    public List<WebSocketSession> getAdmins() {
        return List.copyOf(admins);
    }

    public List<WebSocketSession> getGameMasters() {
        return List.copyOf(gameMasters);
    }

    public List<WebSocketSession> getPlayers() {
        return List.copyOf(players);
    }

    public List<WebSocketSession> getAllSessions() {
        return List.copyOf(allSessions);
    }

    public void sendHeartBeat() {
        Push.message(PushMessage.HEART_BEAT)
                .data(new HeartBeatData())
                .to(Audience.ALL)
                .push();
    }
}
