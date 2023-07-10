package requests.spark.websockets.objects.messages.dataobjects;

import requests.spark.websockets.objects.messages.dataitems.FormulaContextDataItem;
import requests.spark.websockets.objects.messages.mapping.WSDataJSONArrayClass;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;
import requests.spark.websockets.objects.messages.mapping.WSDataTypeScriptClass;

import java.util.List;

public class RequestFormulaContextsData extends WebSocketData {
    @WSDataOutgoing
    @WSDataJSONArrayClass(FormulaContextDataItem.class)
    @WSDataTypeScriptClass(FormulaContextDataItem.class)
    private List<FormulaContextDataItem> formulaContexts = null;

    public List<FormulaContextDataItem> getFormulaContexts() {
        return formulaContexts;
    }

    public void setFormulaContexts(List<FormulaContextDataItem> formulaContexts) {
        this.formulaContexts = formulaContexts;
    }
}
