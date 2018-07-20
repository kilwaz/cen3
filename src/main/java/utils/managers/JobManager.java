package utils.managers;


import error.Error;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class JobManager {
    private static Scheduler scheduler;
    private static JobManager instance;

    private static Logger log = Logger.getLogger(JobManager.class);

    public JobManager() {
        try {
            scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
        } catch (SchedulerException ex) {
            Error.JOB_MANAGER.record().create(ex);
        }

        instance = this;
    }

    public static JobManager getInstance() {
        if (instance == null) {
            new JobManager();
        }
        return instance;
    }

    public void stopAllJobs() {
        try {
            scheduler.shutdown();
        } catch (SchedulerException ex) {
            Error.STOP_JOBS.record().create(ex);
        }
    }

    public void stopJob(JobKey jobKey) {
        try {
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException ex) {
            Error.STOP_JOB.record().create(ex);
        }
    }

    public void scheduleJob(JobDetail jobDetail, Trigger trigger) {
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException ex) {
            Error.SCHEDULE_JOB.record().create(ex);
        }
    }
}
