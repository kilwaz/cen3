package requests.spark.websockets.objects.messages.request;

import clarity.definition.Definition;
import clarity.definition.WorksheetConfig;
import clarity.definition.WorksheetConfigDetails;
import data.model.dao.WorksheetConfigDAO;
import log.AppLogger;
import org.apache.logging.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataobjects.UpdateWorksheetConfigData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

import java.util.UUID;

@MessageType("UpdateWorksheetConfig")
@WebSocketDataClass(UpdateWorksheetConfigData.class)
public class UpdateWorksheetConfig extends Message {
    private static Logger log = AppLogger.logger();

    public void process() {
        UpdateWorksheetConfigData updateWorksheetConfigData = (UpdateWorksheetConfigData) this.getWebSocketData();

        Definition definition = Definition.load(UUID.fromString(updateWorksheetConfigData.getDefinitionId()), Definition.class);
        WorksheetConfig worksheetConfig = new WorksheetConfigDAO().getWorksheetConfig(updateWorksheetConfigData.getWorksheetConfigName());

        WorksheetConfigDetails worksheetConfigDetails = WorksheetConfigDetails.create(WorksheetConfigDetails.class);

        worksheetConfigDetails.worksheetConfig(worksheetConfig);
        worksheetConfigDetails.definition(definition);
        worksheetConfigDetails.columnTitle("New");
        worksheetConfigDetails.columnType("Text");
        worksheetConfigDetails.columnOrder(5);
        worksheetConfigDetails.save();

        log.info(updateWorksheetConfigData.getWorksheetConfigName() + " - " + definition.getName());
    }
}
