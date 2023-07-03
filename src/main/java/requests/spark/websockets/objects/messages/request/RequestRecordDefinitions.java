package requests.spark.websockets.objects.messages.request;

import clarity.definition.Definitions;
import clarity.definition.RecordDefinition;
import log.AppLogger;
import org.apache.logging.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataitems.RecordDefinitionDataItem;
import requests.spark.websockets.objects.messages.dataobjects.RequestRecordDefinitionsData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

import java.util.ArrayList;
import java.util.List;

@MessageType("RequestRecordDefinitions")
@WebSocketDataClass(RequestRecordDefinitionsData.class)
public class RequestRecordDefinitions extends Message {
    private static Logger log = AppLogger.logger();

    public void process() {
        RequestRecordDefinitionsData recordDefinitionsData = (RequestRecordDefinitionsData) this.getWebSocketData();

        List<RecordDefinitionDataItem> recordDefinitionDataItems = new ArrayList<>();
        for (RecordDefinition recordDefinition : Definitions.getInstance().getAllRecordDefinitions()) {
            recordDefinitionDataItems.add(new RecordDefinitionDataItem(recordDefinition));
        }

        recordDefinitionsData.setRecordDefinitions(recordDefinitionDataItems);
    }
}
