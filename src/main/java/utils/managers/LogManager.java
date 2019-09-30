package utils.managers;

import error.Error;
import log.AppLogger;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import utils.ApplicationParams;

import java.io.File;
import java.io.IOException;

public class LogManager {
    private static Logger log = AppLogger.logger();
    private static LogManager instance;
    private String logOutputFilePath = null;

    private LogManager() {
        instance = this;

        File logDirectory = new File(getLogOutputDirectoryPath());
        if (!logDirectory.exists()) {
            logDirectory.mkdir();
        }
    }

    public static LogManager getInstance() {
        if (instance == null) {
            instance = new LogManager();
        }
        return instance;
    }

    private String getLogOutputDirectoryPath() {
        if ("".equals(ApplicationParams.getLogDirectory())) {
            String path = "/opt/wildfly/standalone/log/";

            return path;
        } else {
            return ApplicationParams.getLogDirectory();
        }
    }

    public String getLogOutputDirectoryCanonicalPath() {
        try {
            return new File(getLogOutputDirectoryPath()).getCanonicalPath();
        } catch (IOException ex) {
            Error.SDE_FILE_NOT_FOUND.record().additionalInformation("Unable to find log directory path").create(ex);
        }
        return "Unable to find log file directory";
    }

    public String getLogOutputCanonicalPath() {
        try {
            return new File(getLogOutputFilePath()).getCanonicalPath();
        } catch (IOException ex) {
            Error.SDE_FILE_NOT_FOUND.record().additionalInformation("Unable to find log file path").create(ex);
        }
        return "Unable to find log file path";
    }

    private String getLogOutputFilePath() {
        if (logOutputFilePath == null) {
            DateTime dt = new DateTime();
            DateTimeFormatter fmt = DateTimeFormat.forPattern("ddMMMyyyy HHmmss");
            String str = fmt.print(dt);
            logOutputFilePath = getLogOutputDirectoryPath() + "SDE3 - Started " + str + ".log";
        }

        return logOutputFilePath;
    }
}
