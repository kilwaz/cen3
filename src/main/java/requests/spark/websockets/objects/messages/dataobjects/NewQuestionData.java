package requests.spark.websockets.objects.messages.dataobjects;

import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;

public class NewQuestionData extends WebSocketData {
    @WSDataOutgoing
    private String questionText = null;

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
}
