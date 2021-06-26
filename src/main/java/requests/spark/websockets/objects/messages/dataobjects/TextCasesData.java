package requests.spark.websockets.objects.messages.dataobjects;

import requests.spark.websockets.objects.messages.mapping.WSDataIncoming;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;

public class TextCasesData extends WebSocketData {
    public static final int SENTENCE_CASE = 1;
    public static final int LOWER_CASE = 2;
    public static final int UPPER_CASE = 3;
    public static final int CAPITALISED_CASE = 4;
    public static final int ALTERNATING_CASE = 5;
    public static final int TITLE_CASE = 6;
    public static final int INVERSE_CASE = 7;

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
