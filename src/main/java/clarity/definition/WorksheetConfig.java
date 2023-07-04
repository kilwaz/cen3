package clarity.definition;

import data.model.DatabaseObject;
import requests.spark.websockets.objects.messages.dataitems.WebWorksheetConfigDataItem;

public class WorksheetConfig extends DatabaseObject {

    private String columnTitle = "";
    private Definition definition = null;
    private String columnType = "";
    private String dataType = "";
    private Integer columnOrder = 0;
    private String textAlign = "";
    private String colour = "";

    public WorksheetConfig() {
        super();
    }

    public WorksheetConfig(String columnTitle, Definition definition, String columnType, String dataType, Integer columnOrder, String textAlign, String colour) {
        this.columnTitle = columnTitle;
        this.definition = definition;
        this.columnType = columnType;
        this.dataType = dataType;
        this.columnOrder = columnOrder;
        this.textAlign = textAlign;
        this.colour = colour;
    }

    public String getColumnTitle() {
        return columnTitle;
    }

    public void columnTitle(String columnTitle) {
        this.columnTitle = columnTitle;
    }

    public Definition getDefinition() {
        return definition;
    }

    public String getDefinitionUUID() {
        return definition.getUuidString();
    }

    public void definition(Definition definition) {
        this.definition = definition;
    }

    public String getColumnType() {
        return columnType;
    }

    public void columnType(String columnType) {
        this.columnType = columnType;
    }

    public String getDataType() {
        return dataType;
    }

    public void dataType(String dataType) {
        this.dataType = dataType;
    }

    public Integer getColumnOrder() {
        return columnOrder;
    }

    public void columnOrder(Integer columnOrder) {
        this.columnOrder = columnOrder;
    }

    public String getTextAlign() {
        return textAlign;
    }

    public void textAlign(String textAlign) {
        this.textAlign = textAlign;
    }

    public String getColour() {
        return colour;
    }

    public void colour(String colour) {
        this.colour = colour;
    }

    public WebWorksheetConfigDataItem getAsWebWorksheetConfig() {
        WebWorksheetConfigDataItem webWorksheetConfig = new WebWorksheetConfigDataItem();
        webWorksheetConfig.setName(columnTitle);
        webWorksheetConfig.setColumnType(columnType);
        webWorksheetConfig.setDefinitionName(definition.getName());
        return webWorksheetConfig;
    }
}
