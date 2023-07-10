package requests.spark.websockets.objects.messages.dataitems;

import clarity.definition.WorksheetConfigDetails;
import requests.spark.websockets.objects.JSONWeb;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;

public class WebWorksheetConfigDataItem extends JSONWeb {
    @WSDataReference()
    private String uuid = null;

    @WSDataReference()
    private String name = null;

    @WSDataReference()
    private String definitionName = null;

    @WSDataReference()
    private String definitionId = null;

    @WSDataReference()
    private Integer columnOrder = null;

    @WSDataReference()
    private String columnType = null;

    public WebWorksheetConfigDataItem(WorksheetConfigDetails worksheetConfigDetails) {
        this.uuid = worksheetConfigDetails.getUuidString();
        this.name = worksheetConfigDetails.getColumnTitle();
        this.definitionName = worksheetConfigDetails.getDefinition().getName();
        this.definitionId = worksheetConfigDetails.getDefinition().getUuidString();
        this.columnOrder = worksheetConfigDetails.getColumnOrder();
        this.columnType = worksheetConfigDetails.getColumnType();
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

    public Integer getColumnOrder() {
        return columnOrder;
    }

    public void setColumnOrder(Integer columnOrder) {
        this.columnOrder = columnOrder;
    }

    public String getDefinitionId() {
        return definitionId;
    }

    public void setDefinitionId(String definitionId) {
        this.definitionId = definitionId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
