package requests.spark.websockets.objects.messages.request;

import log.AppLogger;
import org.apache.logging.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataobjects.DownloadTestData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

@MessageType("DownloadTest")
@WebSocketDataClass(DownloadTestData.class)
public class DownloadTest extends Message {
    private static Logger log = AppLogger.logger();

    public void process() {
        DownloadTestData downloadTestData = (DownloadTestData) this.getWebSocketData();

        try {
            String filePath = "C:\\Users\\alex\\Downloads\\Arup HR Roles 2023\\Uploads\\Test.xlsx";
            File file = new File(filePath);
            byte[] fileContent = Files.readAllBytes(file.toPath());
            String encodedString = Base64.getEncoder().encodeToString(fileContent);
            downloadTestData.setContent(encodedString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
