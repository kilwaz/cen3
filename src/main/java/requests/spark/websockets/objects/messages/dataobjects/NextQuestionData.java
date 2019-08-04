package requests.spark.websockets.objects.messages.dataobjects;

import game.actors.Question;
import game.actors.QuestionOption;
import org.json.JSONArray;
import org.json.JSONObject;
import requests.spark.websockets.objects.messages.mapping.WSData;
import requests.spark.websockets.objects.messages.mapping.WSDataJSONArrayClass;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class NextQuestionData extends WebSocketData {
    @WSDataOutgoing
    private UUID nextQuestionUUID;
    @WSDataOutgoing
    private String questionText;
    @WSDataOutgoing
    @WSDataJSONArrayClass(QuestionOption.class)
    private JSONArray options;

    public NextQuestionData(Question nextQuestion) {
        nextQuestionUUID = nextQuestion.getUuid();
        options = new JSONArray();

        List<JSONObject> questionOptionList = new ArrayList<>();
        for (QuestionOption questionOption : nextQuestion.getPossibleOptions()) {
            questionOptionList.add(questionOption.prepareForJSON(WSData.QUESTION_OPTION_UUID, WSData.QUESTION_OPTION_ANSWER_VALUE));
        }

        // Randomise answer order
        Collections.shuffle(questionOptionList);
        for (JSONObject option : questionOptionList) {
            options.put(option);
        }

        questionText = nextQuestion.getQuestionText();
    }

    public UUID getNextQuestionUUID() {
        return nextQuestionUUID;
    }

    public void setNextQuestionUUID(UUID nextQuestionUUID) {
        this.nextQuestionUUID = nextQuestionUUID;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public JSONArray getOptions() {
        return options;
    }

    public void setOptions(JSONArray options) {
        this.options = options;
    }
}
