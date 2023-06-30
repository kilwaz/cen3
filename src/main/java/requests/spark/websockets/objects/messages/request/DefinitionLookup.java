package requests.spark.websockets.objects.messages.request;

import clarity.definition.Definition;
import clarity.definition.Definitions;
import log.AppLogger;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataitems.DefinitionDataItem;
import requests.spark.websockets.objects.messages.dataobjects.DefinitionLookupData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

import java.util.HashMap;

@MessageType("DefinitionLookup")
@WebSocketDataClass(DefinitionLookupData.class)
public class DefinitionLookup extends Message {
    private static Logger log = AppLogger.logger();

    public void process() {
        DefinitionLookupData definitionLookupData = (DefinitionLookupData) this.getWebSocketData();


        String definitionName = definitionLookupData.getName();

        Definitions.getInstance().getDefinition(definitionName);






        JSONArray definitionJSON = new JSONArray();

        HashMap<String, Definition> definitionHashMap = Definitions.getInstance().getDefinitionHashMap();
        for (Definition definition : definitionHashMap.values()) {
            definitionJSON.put(new DefinitionDataItem(definition).prepareForJSON());
        }

        definitionLookupData.setEntries(definitionJSON);
    }
}
