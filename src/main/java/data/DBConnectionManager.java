package data;

import org.apache.log4j.Logger;
import utils.ApplicationParams;
import utils.managers.DatabaseTransactionManager;

import java.util.ArrayList;
import java.util.List;

public class DBConnectionManager {
    private static DBConnectionManager instance;
    private static Logger log = Logger.getLogger(DBConnectionManager.class);
    private List<DBConnection> DBConnectionList = new ArrayList<>();
    private DBConnection applicationConnection;
    private Boolean isConnected = false;

    public DBConnectionManager() {
        instance = this;
    }

    public static DBConnectionManager getInstance() {
        if (instance == null) {
            new DBConnectionManager();
        }
        return instance;
    }

    public void addConnection(DBConnection DBConnection) {
        DBConnectionList.add(DBConnection);
    }

    public void closeConnections() {
        DBConnectionList.forEach(DBConnection::close);
    }

    public Boolean createApplicationConnection() {
        if (applicationConnection != null) {
            DatabaseTransactionManager.getInstance().finaliseTransactions();
            applicationConnection.close();
        }

        applicationConnection = new DBConnection(ApplicationParams.getRemoteDatabaseConnection(), ApplicationParams.getRemoteDatabaseUsername(), ApplicationParams.getRemoteDatabasePassword(), true);

        addConnection(applicationConnection);
        if (!applicationConnection.connect()) {
            return false;
        } else {
            isConnected = true;
        }

        return true;
    }

    public DBConnection getApplicationConnection() {
        return applicationConnection;
    }

    public Boolean isConnected() {
        return isConnected;
    }
}