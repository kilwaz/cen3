package clarity.definition;

import log.AppLogger;
import org.apache.logging.log4j.Logger;

public class DatabaseModelController {
    private static Logger log = AppLogger.logger();
    private static DatabaseModelController instance;

    public DatabaseModelController() {

    }

    public static DatabaseModelController getInstance() {
        if (instance == null) {
            instance = new DatabaseModelController();
        }
        return instance;
    }
}
