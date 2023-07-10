package requests.spark.websockets.objects.messages.dataobjects;

import requests.spark.websockets.objects.messages.mapping.WSDataIncoming;

public class UpdateWorksheetConfigData extends WebSocketData {
    @WSDataIncoming
    private String definitionId = null;

    @WSDataIncoming
    private String worksheetConfigName = null;

    public String getDefinitionId() {
        return definitionId;
    }

    public void setDefinitionId(String definitionId) {
        this.definitionId = definitionId;
    }

    public String getWorksheetConfigName() {
        return worksheetConfigName;
    }

    public void setWorksheetConfigName(String worksheetConfigName) {
        this.worksheetConfigName = worksheetConfigName;
    }
}
