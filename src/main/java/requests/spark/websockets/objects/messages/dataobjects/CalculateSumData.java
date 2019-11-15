package requests.spark.websockets.objects.messages.dataobjects;

import game.actors.Node;
import requests.spark.websockets.objects.messages.mapping.WSDataIncoming;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;
import requests.spark.websockets.objects.messages.mapping.WSDataTypeScriptClass;

public class CalculateSumData extends WebSocketData {
    @WSDataIncoming
    private String formula = null;
    @WSDataOutgoing
    private Double result = null;

    @WSDataOutgoing
    @WSDataTypeScriptClass(Node.class)
    private Node node = null;

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}
