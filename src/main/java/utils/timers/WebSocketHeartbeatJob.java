package utils.timers;


import log.AppLogger;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import requests.spark.websockets.WebSocketManager;
import utils.managers.DatabaseTransactionManager;

public class WebSocketHeartbeatJob implements Job {
    private static Logger log = AppLogger.logger();

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        WebSocketManager.getInstance().sendHeartBeat();
    }
}
