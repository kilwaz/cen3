package log;

import org.apache.log4j.Logger;

public class AppLogger {
    public static Logger logger() {
        StackTraceElement[] stackTraces = Thread.currentThread().getStackTrace();
        return Logger.getLogger(stackTraces[2].getClassName());
    }
}
