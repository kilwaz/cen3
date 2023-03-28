package utils.managers;

import data.DBConnectionManager;
import data.Query;
import data.SelectQuery;
import data.UpdateQuery;
import error.Error;
import log.AppLogger;
import org.apache.logging.log4j.Logger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.TriggerBuilder;
import utils.ApplicationParams;
import utils.Timer;
import utils.timers.TransactionJob;

import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseTransactionManager {
    private static DatabaseTransactionManager databaseTransactionManager;
    private static Logger log = AppLogger.logger();
    private List<Query> pendingQueryList = new ArrayList<>();
    private Boolean inTransaction = false;
    private Timer timeOfLastQuery = new Timer();

    public DatabaseTransactionManager() {
        databaseTransactionManager = this;

        JobDetail transactionJob = JobBuilder.newJob(TransactionJob.class).build();
        //JobDetail dataDataJob = JobBuilder.newJob(DeleteDataJob.class).buildResources();

        SimpleScheduleBuilder transactionSimpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule();
        //SimpleScheduleBuilder deleteDataSimpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule();
        TriggerBuilder transactionTriggerBuilder = TriggerBuilder.newTrigger();
        //TriggerBuilder deleteDataTriggerBuilder = TriggerBuilder.newTrigger();

        transactionSimpleScheduleBuilder.repeatForever().withIntervalInMilliseconds(1000);
        //deleteDataSimpleScheduleBuilder.repeatForever().withIntervalInMilliseconds(5000);

        JobManager.getInstance().scheduleJob(transactionJob, transactionTriggerBuilder.withSchedule(transactionSimpleScheduleBuilder).build());
        //JobManager.getInstance().scheduleJob(dataDataJob, deleteDataTriggerBuilder.withSchedule(deleteDataSimpleScheduleBuilder).buildResources());
        transactionTriggerBuilder.startNow();
        //deleteDataTriggerBuilder.startNow();
    }

    public static synchronized DatabaseTransactionManager getInstance() {
        if (databaseTransactionManager == null) {
            databaseTransactionManager = new DatabaseTransactionManager();
        }
        return databaseTransactionManager;
    }

    public synchronized void addSelect(SelectQuery selectQuery) {
//        log.info("Select - " + selectQuery.getQuery());
//        logQueryParams(selectQuery);
        try {
            if (inTransaction) {
                DBConnectionManager.getInstance().getApplicationConnection().getConnection().commit();
                DBConnectionManager.getInstance().getApplicationConnection().getConnection().setAutoCommit(true);
//                log.info("Committed and transaction ended");
                inTransaction = false;
            }
            pendingQueryList.clear();
            pendingQueryList.add(selectQuery);
        } catch (SQLNonTransientConnectionException ex) {
            attemptReconnection();
            addSelect(selectQuery);
        } catch (SQLException ex) {
            Error.DATABASE_TRANSACTION.record().create(ex);
        }
    }

    public synchronized void scheduledDelete() {
        new UpdateQuery("delete from http_headers where forDelete = 1 limit " + ApplicationParams.getDatabaseDeleteLimit()).execute();
        new UpdateQuery("delete from http_proxies where forDelete = 1 limit " + ApplicationParams.getDatabaseDeleteLimit()).execute();
        new UpdateQuery("delete from recorded_requests where forDelete = 1 limit " + ApplicationParams.getDatabaseDeleteLimit()).execute();
        new UpdateQuery("delete from test_command where forDelete = 1 limit " + ApplicationParams.getDatabaseDeleteLimit()).execute();
        new UpdateQuery("delete from test where forDelete = 1 limit " + ApplicationParams.getDatabaseDeleteLimit()).execute();
    }

    public synchronized void addUpdate(UpdateQuery updateQuery) {
        //log.info("Update - " + updateQuery.getQuery());
        logQueryParams(updateQuery);
        if (!inTransaction) {
            try {
                inTransaction = true;
                DBConnectionManager.getInstance().getApplicationConnection().getConnection().setAutoCommit(false);
                //log.info("Transaction started");
            } catch (SQLNonTransientConnectionException ex) {
                attemptReconnection();
                addUpdate(updateQuery);
            } catch (SQLException ex) {
                Error.DATABASE_TRANSACTION.record().create(ex);
            }
        }

        timeOfLastQuery = new Timer();
        pendingQueryList.add(updateQuery);
    }

    public synchronized void finaliseTransactions() {
        try {
            if (inTransaction) {
                if (DBConnectionManager.getInstance().getApplicationConnection() != null && DBConnectionManager.getInstance().getApplicationConnection().getConnection() != null) {
                    DBConnectionManager.getInstance().getApplicationConnection().getConnection().commit();
                    DBConnectionManager.getInstance().getApplicationConnection().getConnection().setAutoCommit(true);
                    pendingQueryList.clear();
                    inTransaction = false;
                }
            }
        } catch (SQLException ex) {
            Error.DATABASE_TRANSACTION.record().create(ex);
        }
    }

    private void logQueryParams(Query query) {
        List<Object> params = query.getParameters();
        for (Object o : params) {
            if (o != null) {
                //log.info("   " + o.toString());
            } else {
                log.info("   null");
            }
        }
    }

    public void checkIfNeedToFinalise() {
        if (inTransaction) {
            if (pendingQueryList.size() > 0 && timeOfLastQuery.getTimeSince() > 1000) {
                //log.info("Committing " + pendingQueryList.size() + " query transactions after 1000ms");
                finaliseTransactions();
            }
        }
    }

    private void attemptReconnection() {
        //log.info("Attempt reconnect");
        inTransaction = false;
        if (!DBConnectionManager.getInstance().isConnected()) {
            //log.info("Doing the reconnect");
            DBConnectionManager.getInstance().createApplicationConnection();
        }
    }
}
