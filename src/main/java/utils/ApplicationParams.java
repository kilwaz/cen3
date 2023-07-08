package utils;

import error.Error;
import log.AppLogger;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import utils.managers.LogManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

public class ApplicationParams {
    public final static String ORACLE_DIALECT = "ORACLE";
    public final static String MYSQL_DIALECT = "MYSQL";

    // Database information - Defaults are displayed here and can be overridden by the SDE.xml file
    private static Boolean DATABASE_ENABLED = true;

    private static String REMOTE_DATABASE_USERNAME = "";
    //    private static String REMOTE_DATABASE_CONNECTION = "jdbc:mysql://uk-mysql:3306/clarity?autoReconnect=true&useSSL=false";
//    private static String REMOTE_DATABASE_PASSWORD = "ClarityDatabase123";
//    private static String REMOTE_DATABASE_CONNECTION = "jdbc:mysql://localhost:3306/clarity?autoReconnect=true&useSSL=false";
    private static String REMOTE_DATABASE_CONNECTION = "";
    private static String REMOTE_DATABASE_DIALECT = ORACLE_DIALECT;
    private static String REMOTE_DATABASE_PASSWORD = "";

    // Uploaded files settings
    private static String UPLOAD_FILE_TMP_LOCATION = "/home/kilwaz/tmp/";

    // Log settings
    private static Boolean IN_APP_LOG_VIEW = false;
    private static String LOG_DIRECTORY = "";

    // Database
    private static Integer DATABASE_DELETE_LIMIT = 100;

    // Data clearing / loading
    private static Boolean CLEAR_DOWN_CONFIG_TABLES = false;
    private static Boolean CLEAR_DOWN_DATA_TABLES = false;
    private static Boolean IMPORT_CONFIG_WHEN_STARTING = false;
    private static Boolean IMPORT_DATA_WHEN_STARTING = false;

    // Paths
    private static String CONFIG_JSON_PATH = "";
    private static String BASE_DATA_PATH = "";

    private static Logger log = AppLogger.logger();

    public static String getRemoteDatabaseConnection() {
        return REMOTE_DATABASE_CONNECTION;
    }

    public static String getRemoteDatabaseDialect() {
        return REMOTE_DATABASE_DIALECT;
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

    public static Boolean getImportDataWhenStarting() {
        return IMPORT_DATA_WHEN_STARTING;
    }

    public static Boolean getImportConfigWhenStarting() {
        return IMPORT_CONFIG_WHEN_STARTING;
    }

    public static Boolean getClearDownConfigTables() {
        return CLEAR_DOWN_CONFIG_TABLES;
    }

    public static Boolean getClearDownDataTables() {
        return CLEAR_DOWN_DATA_TABLES;
    }

    public static String getConfigJsonPath() {
        return CONFIG_JSON_PATH;
    }

    public static String getBaseDataPath() {
        return BASE_DATA_PATH;
    }

    public static void loadFromXML() {
        File jsonFileToLoad = new File("C:\\Users\\alex\\Downloads\\Arup HR Roles 2023\\Uploads\\appconfig.json");

        if (jsonFileToLoad.exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(jsonFileToLoad);
                byte[] data = new byte[(int) jsonFileToLoad.length()];
                fis.read(data);
                fis.close();

                String rawData = new String(data, "UTF-8");

                JSONObject jsonObject = new JSONObject(Objects.requireNonNullElse(rawData, ""));

                JSONObject database = jsonObject.getJSONObject("database");
                REMOTE_DATABASE_CONNECTION = database.getString("remoteDatabaseConnection");
                REMOTE_DATABASE_DIALECT = database.getString("remoteDatabaseDialect");

                REMOTE_DATABASE_USERNAME = database.getString("remoteDatabaseUsername");
                REMOTE_DATABASE_PASSWORD = database.getString("remoteDatabasePassword");

                JSONObject dataImport = jsonObject.getJSONObject("dataImport");
                CLEAR_DOWN_CONFIG_TABLES = dataImport.getBoolean("clearDownConfigTables");
                CLEAR_DOWN_DATA_TABLES = dataImport.getBoolean("clearDownDataTables");
                IMPORT_CONFIG_WHEN_STARTING = dataImport.getBoolean("importConfigWhenStarting");
                IMPORT_DATA_WHEN_STARTING = dataImport.getBoolean("importDataWhenStarting");

                JSONObject paths = jsonObject.getJSONObject("paths");
                CONFIG_JSON_PATH = paths.getString("configJsonPath");
                BASE_DATA_PATH = paths.getString("baseDataPath");

                log.info("Loaded database from config file");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
