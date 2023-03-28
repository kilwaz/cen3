package log;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.config.Configurator;

public class AppLogger {
    public static Logger logger() {
        StackTraceElement[] stackTraces = Thread.currentThread().getStackTrace();
        Configurator.setLevel(stackTraces[2].getClassName(), Level.DEBUG);
        return LogManager.getLogger(stackTraces[2].getClassName());
    }
}
