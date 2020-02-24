package utils;

import error.Error;
import utils.managers.LogManager;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ApplicationParams {
    // Application information
    public final static String APP_TITLE = "Clarity";
    public final static String APP_VERSION = "v0.3.0";

    // Database information - Defaults are displayed here and can be overridden by the SDE.xml file
    private static Boolean DATABASE_ENABLED = true;

    private static String REMOTE_DATABASE_USERNAME = "clarity";
    //private static String REMOTE_DATABASE_CONNECTION = "jdbc:mysql://uk-mysql:3306/clarity?autoReconnect=true&useSSL=false";
    //private static String REMOTE_DATABASE_PASSWORD = "ClarityDatabase123";
    private static String REMOTE_DATABASE_CONNECTION = "jdbc:mysql://localhost:4000/clarity?autoReconnect=true&useSSL=false";
    private static String REMOTE_DATABASE_PASSWORD = "joshthe12";

    // Uploaded files settings
    private static String UPLOAD_FILE_TMP_LOCATION = "/home/kilwaz/tmp/";

    // Log settings
    private static Boolean IN_APP_LOG_VIEW = false;
    private static String LOG_DIRECTORY = "";

    // Database
    private static Integer DATABASE_DELETE_LIMIT = 100;

    public static String getRemoteDatabaseConnection() {
        return REMOTE_DATABASE_CONNECTION;
    }

    public static void setRemoteDatabaseConnection(String databaseConnection) {
        REMOTE_DATABASE_CONNECTION = databaseConnection;
    }

    public static String getRemoteDatabaseUsername() {
        return REMOTE_DATABASE_USERNAME;
    }

    public static void setRemoteDatabaseUsername(String remoteDatabaseUsername) {
        REMOTE_DATABASE_USERNAME = remoteDatabaseUsername;
    }

    public static String getRemoteDatabasePassword() {
        return REMOTE_DATABASE_PASSWORD;
    }

    public static void setRemoteDatabasePassword(String remoteDatabasePassword) {
        REMOTE_DATABASE_PASSWORD = remoteDatabasePassword;
    }

    public static Boolean getInAppLogView() {
        return IN_APP_LOG_VIEW;
    }

    public static void setInAppLogView(Boolean inAppLogView) {
        IN_APP_LOG_VIEW = inAppLogView;
    }

    public static Integer getDatabaseDeleteLimit() {
        return DATABASE_DELETE_LIMIT;
    }

    public static String getJVMProperty(String key) {
        return System.getProperties().getProperty(key);
    }

    public static String getLogDirectory() {
        return LOG_DIRECTORY;
    }

    public static String getConfiguredLogDirectory() {
        if ("".equals(LOG_DIRECTORY)) {
            LOG_DIRECTORY = LogManager.getInstance().getLogOutputDirectoryCanonicalPath();
        }
        return LOG_DIRECTORY;
    }

    public static void setLogDirectory(String logDirectory) {
        LOG_DIRECTORY = logDirectory;
    }

    public static String getMachineName() {
        String hostname = "Unknown Machine";

        try {
            InetAddress address = InetAddress.getLocalHost();
            hostname = address.getHostName();
        } catch (UnknownHostException ex) {
            Error.CANNOT_RESOLVE_HOST_THIS_MACHINE.record().create(ex);
        }

        return hostname;
    }

    public static String getUploadFileTmpLocation() {
        return UPLOAD_FILE_TMP_LOCATION;
    }

    public static Boolean getDatabaseEnabled() {
        return DATABASE_ENABLED;
    }
}
