package requests.spark.websockets.objects.messages.dataitems;

import clarity.definition.Definition;
import org.json.JSONArray;
import requests.spark.websockets.objects.JSONWeb;
import requests.spark.websockets.objects.messages.mapping.WSDataJSONArrayClass;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;

import java.util.List;

public class RecordDefinitionDataItem extends JSONWeb {
    @WSDataReference()
    private String uuid = null;

    @WSDataReference()
    private String name = null;

    @WSDataOutgoing
    @WSDataJSONArrayClass(String.class)
    @WSDataReference()
    private JSONArray definitionIds = null;

    public RecordDefinitionDataItem(clarity.definition.RecordDefinition recordDefinitionClarity) {
        this.uuid = recordDefinitionClarity.getUuidString();
        this.name = recordDefinitionClarity.getName();

        List<Definition> definitions = recordDefinitionClarity.getDefinitions();
        this.definitionIds = new JSONArray();
        for (Definition definition : definitions) {
            this.definitionIds.put(definition.getUuidString());
        }
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JSONArray getDefinitionIds() {
        return definitionIds;
    }

    public void setDefinitionIds(JSONArray definitionIds) {
        this.definitionIds = definitionIds;
    }
}
