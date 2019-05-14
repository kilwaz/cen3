package utils.timers;


import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import requests.spark.websockets.WebSocketManager;
import utils.managers.DatabaseTransactionManager;

public class WebSocketHeartbeatJob implements Job {
    private static Logger log = Logger.getLogger(WebSocketHeartbeatJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        WebSocketManager.getInstance().sendHeartBeat();
    }
}
