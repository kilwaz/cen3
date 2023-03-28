package utils.timers;


import log.AppLogger;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import utils.managers.DatabaseTransactionManager;

public class TransactionJob implements Job {
    private static Logger log = AppLogger.logger();

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        DatabaseTransactionManager.getInstance().checkIfNeedToFinalise();
    }
}
