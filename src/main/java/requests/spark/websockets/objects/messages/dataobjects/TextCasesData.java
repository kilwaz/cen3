package requests.spark.websockets.objects.messages.dataobjects;

import requests.spark.websockets.objects.messages.mapping.WSDataIncoming;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;

public class TextCasesData extends WebSocketData {
    @WSDataIncoming
    private String textToProcess = null;
    @WSDataIncoming
    private Integer textFunction = null;

    @WSDataOutgoing
    private String textResult = null;

    public String getTextToProcess() {
        return textToProcess;
    }

    public void setTextToProcess(String textToProcess) {
        this.textToProcess = textToProcess;
    }

    public String getTextResult() {
        return textResult;
    }

    public void setTextResult(String textResult) {
        this.textResult = textResult;
    }

    public Integer getTextFunction() {
        return textFunction;
    }

    public void setTextFunction(Integer textFunction) {
        this.textFunction = textFunction;
    }
}
