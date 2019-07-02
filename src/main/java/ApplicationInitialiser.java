import core.builders.requests.RequestMapper;
import core.builders.requests.WebSocketMessageMapping;
import game.GameManager;
import game.actors.Player;
import org.apache.log4j.Logger;
import requests.spark.websockets.objects.messages.mapping.WSData;
import utils.AppManager;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ApplicationInitialiser {
    private static Logger log = Logger.getLogger(ApplicationInitialiser.class);

    public static void main(String[] args) {
        AppManager.init();
        RequestMapper.buildMappings();
        WebSocketMessageMapping.buildMappings();
        GameManager.getInstance().createNewGame();

        // Get IP address of laptop
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }


        Player player = GameManager.getInstance().getCurrentGame().createPlayer();
        log.info(player.prepareForJSON());
        log.info("Gap");
        log.info(player.prepareForJSON(WSData.PLAYER_ID, WSData.PLAYER_UUID));
        log.info("Gap");
        log.info(player.prepareForJSON(WSData.PLAYER_UUID));


        // Make a QR code
//        File qrCodeFile = new File("/Users/alexbrown/IdeaProjects/cen3/src/main/ngapp/src/assets/images/qrcode.jpg");
//        FileOutputStream fop = null;
//        try {
//            fop = new FileOutputStream(qrCodeFile);
//            QRCode.from("http://" + inetAddress.getHostAddress()).to(ImageType.JPG).writeTo(fop);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

//        log.info("QRCode has been generated and written " + inetAddress.getHostAddress());
        log.info("Server running on " + inetAddress.getHostAddress());
    }
}