package requests.spark.websockets.objects.messages.dataitems;

import requests.spark.websockets.objects.JSONWeb;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;

public class RecordDefinitionDataItem extends JSONWeb {
    @WSDataReference()
    private String uuid = null;

    @WSDataReference()
    private String name = null;

    public RecordDefinitionDataItem(clarity.definition.RecordDefinition recordDefinitionClarity) {
        this.uuid = recordDefinitionClarity.getUuidString();
        this.name = recordDefinitionClarity.getName();
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
}
