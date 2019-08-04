package requests.spark.websockets.objects.messages.dataobjects;

import game.Game;
import game.GameManager;
import game.actors.*;
import org.json.JSONArray;
import org.json.JSONObject;
import requests.spark.websockets.objects.messages.mapping.WSData;
import requests.spark.websockets.objects.messages.mapping.WSDataJSONArrayClass;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;

import java.util.List;
import java.util.UUID;

public class QuestionResultsData extends WebSocketData {
    @WSDataOutgoing
    private UUID questionUUID;
    @WSDataOutgoing
    @WSDataJSONArrayClass(QuestionOption.class)
    private JSONArray questionOptions;
    @WSDataOutgoing
    @WSDataJSONArrayClass(PlayerResult.class)
    private JSONArray playerResults;
    @WSDataOutgoing
    private UUID correctOptionUUID;

    public QuestionResultsData(Question question, List<Player> players) {
        Game game = GameManager.getInstance().getCurrentGame();
        questionUUID = question.getUuid();

        questionOptions = new JSONArray();
        for (QuestionOption questionOption : question.getPossibleOptions()) {
            questionOptions.put(questionOption.prepareForJSON(WSData.QUESTION_OPTION_UUID, WSData.QUESTION_OPTION_ANSWER_VALUE, WSData.QUESTION_OPTION_COUNTED_ANSWERS));
        }

        correctOptionUUID = question.getCorrectOption().getUuid();

        playerResults = new JSONArray();
        for (Player player : players) {
            JSONObject playerObject = new JSONObject();

            playerObject.put("playerUUID", player.getUuid());
            Answer answer = game.findAnswer(player);
            if (answer != null) {
                playerObject.put("isCorrectAnswer", question.getCorrectOption().getUuid().toString().equals(answer.getAnswerValue()));
            }

            playerResults.put(playerObject);
        }
    }

    public UUID getQuestionUUID() {
        return questionUUID;
    }

    public void setQuestionUUID(UUID questionUUID) {
        this.questionUUID = questionUUID;
    }

    public JSONArray getQuestionOptions() {
        return questionOptions;
    }

    public void setQuestionOptions(JSONArray questionOptions) {
        this.questionOptions = questionOptions;
    }

    public JSONArray getPlayerResults() {
        return playerResults;
    }

    public void setPlayerResults(JSONArray playerResults) {
        this.playerResults = playerResults;
    }

    public UUID getCorrectOptionUUID() {
        return correctOptionUUID;
    }

    public void setCorrectOptionUUID(UUID correctOptionUUID) {
        this.correctOptionUUID = correctOptionUUID;
    }
}
