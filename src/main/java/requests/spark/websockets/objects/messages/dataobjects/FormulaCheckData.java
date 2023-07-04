package requests.spark.websockets.objects.messages.dataobjects;

import requests.spark.websockets.objects.messages.dataitems.FormulaDataItem;
import requests.spark.websockets.objects.messages.mapping.WSDataIncoming;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;
import requests.spark.websockets.objects.messages.mapping.WSDataTypeScriptClass;

public class FormulaCheckData extends WebSocketData {
    // INCOMING
    @WSDataIncoming
    private String formulaToCheck = null;

    // OUTGOING
    @WSDataOutgoing
    private String result = null;

    @WSDataOutgoing
    @WSDataTypeScriptClass(FormulaDataItem.class)
    private FormulaDataItem formula = null;

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

    public void setFormula(FormulaDataItem formula) {
        this.formula = formula;
    }

    public FormulaDataItem getFormula() {
        return formula;
    }
}
