package utils;

import data.DBConnectionManager;

public class AppManager {
    private static DBConnectionManager dbConnectionManager;

    public static void init() {
        if (ApplicationParams.getDatabaseEnabled()) {
            dbConnectionManager = DBConnectionManager.getInstance();
            dbConnectionManager.createApplicationConnection();
        }
    }
}
