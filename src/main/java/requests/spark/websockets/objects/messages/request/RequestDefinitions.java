package requests.spark.websockets.objects.messages.request;

import clarity.definition.Definition;
import clarity.definition.RecordDefinition;
import log.AppLogger;
import org.apache.logging.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataitems.DefinitionDataItem;
import requests.spark.websockets.objects.messages.dataobjects.RequestDefinitionsData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

import java.util.ArrayList;
import java.util.List;

@MessageType("RequestDefinitions")
@WebSocketDataClass(RequestDefinitionsData.class)
public class RequestDefinitions extends Message {
    private static Logger log = AppLogger.logger();

    public void process() {
        RequestDefinitionsData definitionsData = (RequestDefinitionsData) this.getWebSocketData();

        String definitionName = definitionsData.getRequestedRecordDefinitionName();
        RecordDefinition recordDefinition = clarity.definition.Definitions.getInstance().getRecordDefinition(definitionName);

        List<DefinitionDataItem> recordDefinitionDataItems = new ArrayList<>();
        for (Definition definition : recordDefinition.getDefinitions()) {
            recordDefinitionDataItems.add(new DefinitionDataItem(definition));
        }

        definitionsData.setDefinitions(recordDefinitionDataItems);
    }
}
