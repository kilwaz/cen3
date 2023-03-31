package requests.spark.websockets.objects.messages.request;

import log.AppLogger;
import org.apache.logging.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataobjects.FileUploadData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;
import utils.managers.FileUploadManager;

@MessageType("FileUpload")
@WebSocketDataClass(FileUploadData.class)
public class FileUpload extends Message {
    private static Logger log = AppLogger.logger();

    public void process() {
        FileUploadData fileUploadData = (FileUploadData) this.getWebSocketData();
        FileUploadManager.getInstance().processNewFileRequest(fileUploadData);
    }
}
