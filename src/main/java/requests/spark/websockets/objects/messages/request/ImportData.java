package requests.spark.websockets.objects.messages.request;

import clarity.load.Load;
import log.AppLogger;
import org.apache.logging.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataobjects.ImportDataData;
import requests.spark.websockets.objects.messages.dataobjects.RecalculateHierarchyData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;
import utils.ApplicationParams;
import utils.HierarchyUtils;

import java.io.File;

@MessageType("ImportData")
@WebSocketDataClass(ImportDataData.class)
public class ImportData extends Message {
    private static Logger log = AppLogger.logger();

    public void process() {
        ImportDataData importDataData = (ImportDataData) this.getWebSocketData();

        log.info("Starting Data Load");

        File fileExcel = new File(ApplicationParams.getBaseDataPath());
        Load.excel(fileExcel).process();

        log.info("Finished Data Load");
    }
}
