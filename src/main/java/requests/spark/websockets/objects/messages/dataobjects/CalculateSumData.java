package requests.spark.websockets.objects.messages.dataobjects;

import game.actors.Formula;
import requests.spark.websockets.objects.messages.mapping.WSDataIncoming;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;
import requests.spark.websockets.objects.messages.mapping.WSDataTypeScriptClass;

public class CalculateSumData extends WebSocketData {
    // INCOMING
    @WSDataIncoming
    private String formulaToProcess = null;

    // OUTGOING
    @WSDataOutgoing
    private Double result = null;

    @WSDataOutgoing
    @WSDataTypeScriptClass(Formula.class)
    private Formula formula = null;

    public String getFormulaToProcess() {
        return formulaToProcess;
    }

    public void setFormulaToProcess(String formulaToProcess) {
        this.formulaToProcess = formulaToProcess;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public void setFormula(Formula formula) {
        this.formula = formula;
    }

    public Formula getFormula() {
        return formula;
    }
}
