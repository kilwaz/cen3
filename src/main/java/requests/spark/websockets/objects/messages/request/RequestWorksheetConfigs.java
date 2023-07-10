package requests.spark.websockets.objects.messages.request;

import clarity.definition.WorksheetConfig;
import data.model.dao.WorksheetConfigDAO;
import log.AppLogger;
import org.apache.logging.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataitems.WorksheetConfigDataItem;
import requests.spark.websockets.objects.messages.dataobjects.RequestWorksheetConfigsData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

import java.util.ArrayList;
import java.util.List;

@MessageType("RequestWorksheetConfigs")
@WebSocketDataClass(RequestWorksheetConfigsData.class)
public class RequestWorksheetConfigs extends Message {
    private static Logger log = AppLogger.logger();

    public void process() {
        RequestWorksheetConfigsData requestWorksheetConfigData = (RequestWorksheetConfigsData) this.getWebSocketData();

        List<WorksheetConfigDataItem> worksheetConfigDataItems = new ArrayList<>();
        for (WorksheetConfig worksheetConfig : new WorksheetConfigDAO().getAllWorksheetConfigs()) {
            worksheetConfigDataItems.add(new WorksheetConfigDataItem(worksheetConfig));
        }

        requestWorksheetConfigData.setWorksheetConfigs(worksheetConfigDataItems);
    }
}
