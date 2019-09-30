import core.builders.requests.RequestMapper;
import core.builders.requests.WebSocketMessageMapping;
import game.GameManager;
import log.AppLogger;
import org.apache.log4j.Logger;
import utils.AppManager;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ApplicationInitialiser {
    private static Logger log = AppLogger.logger();

    public static void main(String[] args) {
        AppManager.init();
        RequestMapper.buildMappings();
        WebSocketMessageMapping.buildMappings();
//        GameManager.getInstance().createNewGame();

        // Get IP address of server machine
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        log.info("Server running on " + inetAddress.getHostAddress());
    }
}