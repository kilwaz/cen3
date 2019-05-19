package requests.spark.websockets.objects.messages.outgoing;

import game.actors.Question;
import game.actors.QuestionOption;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


// This is sent by the server to the players with details of the next question
@MessageType("NextQuestion")
public class NextQuestion extends Message {
    private static Logger log = Logger.getLogger(NextQuestion.class);

    private Question nextQuestion = null;

    public NextQuestion nextQuestion(Question nextQuestion) {
        this.nextQuestion = nextQuestion;
        return this;
    }

    public void prepareToSend() {
        addResponseData("nextQuestionUUID", nextQuestion.getUuid());

        JSONArray options = new JSONArray();
        List<JSONObject> jsonObjectList = new ArrayList<>();
        for (QuestionOption questionOption : nextQuestion.getPossibleOptions()) {
            JSONObject option = new JSONObject();

            option.put("optionUUID", questionOption.getUuid());
            option.put("optionAnswer", questionOption.getAnswerValue());

            jsonObjectList.add(option);
        }

        // Randomise answer order
        Collections.shuffle(jsonObjectList);
        for (JSONObject option : jsonObjectList) {
            options.put(option);
        }

        addResponseData("options", options);
        addResponseData("questionText", nextQuestion.getQuestionText());
    }
}
