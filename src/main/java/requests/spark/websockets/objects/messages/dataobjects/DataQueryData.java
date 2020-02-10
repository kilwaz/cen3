package requests.spark.websockets.objects.messages.dataobjects;

import requests.spark.websockets.objects.messages.mapping.WSDataIncoming;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;

public class DataQueryData extends WebSocketData {
    // INCOMING
    @WSDataIncoming
    private String formulaToCheck = null;

    // OUTGOING
    @WSDataOutgoing
    private String result = null;

    public String getFormulaToCheck() {
        return formulaToCheck;
    }

    public void setFormulaToCheck(String formulaToCheck) {
        this.formulaToCheck = formulaToCheck;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
