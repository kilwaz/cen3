package requests.spark.websockets.objects.messages.dataitems;

import requests.spark.websockets.objects.JSONWeb;
import requests.spark.websockets.objects.messages.mapping.WSData;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;

public class WebWorksheetConfig extends JSONWeb {
    @WSDataReference(WSData.WORKSHEET_CONFIG_NAME)
    private String name = null;

    @WSDataReference(WSData.WORKSHEET_CONFIG_DEFINITION)
    private String definitionName = null;

    @WSDataReference(WSData.WORKSHEET_CONFIG_DEFINITION)
    private String columnType = null;

    public WebWorksheetConfig() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefinitionName() {
        return definitionName;
    }

    public void setDefinitionName(String definitionName) {
        this.definitionName = definitionName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }
}
