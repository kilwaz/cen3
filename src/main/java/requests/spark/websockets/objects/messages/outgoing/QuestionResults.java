package requests.spark.websockets.objects.messages.outgoing;

import game.actors.Question;
import game.actors.QuestionOption;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;

@MessageType("QuestionResults")
public class QuestionResults extends Message {
    private static Logger log = Logger.getLogger(QuestionResults.class);

    private Question question = null;

    public QuestionResults question(Question question) {
        this.question = question;
        return this;
    }

    public void prepareToSend() {
        addResponseData("questionUUID", question.getUuid());

        JSONArray options = new JSONArray();
        for (QuestionOption questionOption : question.getPossibleOptions()) {
            JSONObject option = new JSONObject();

            option.put("optionUUID", questionOption.getUuid());
            option.put("optionAnswerCount", questionOption.getCountedAnswers());
            options.put(option);
        }

        addResponseData("options", options);
        addResponseData("correctOptionUUID", question.getCorrectOption().getUuid());
    }
}
