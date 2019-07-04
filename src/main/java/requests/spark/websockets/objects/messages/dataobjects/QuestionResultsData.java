package requests.spark.websockets.objects.messages.dataobjects;

import game.Game;
import game.GameManager;
import game.actors.Answer;
import game.actors.Player;
import game.actors.Question;
import game.actors.QuestionOption;
import org.json.JSONArray;
import org.json.JSONObject;
import requests.spark.websockets.objects.messages.mapping.WSData;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;

import java.util.List;
import java.util.UUID;

public class QuestionResultsData extends WebSocketData {

    @WSDataOutgoing
    private UUID questionUUID;
    @WSDataOutgoing
    private JSONArray options;
    @WSDataOutgoing
    private JSONArray playerArray;
    @WSDataOutgoing
    private UUID correctOptionUUID;

    public QuestionResultsData(Question question, List<Player> players) {
        Game game = GameManager.getInstance().getCurrentGame();
        questionUUID = question.getUuid();

        options = new JSONArray();
        for (QuestionOption questionOption : question.getPossibleOptions()) {
            options.put(questionOption.prepareForJSON(WSData.QUESTION_OPTION_UUID, WSData.QUESTION_OPTION_ANSWER_VALUE, WSData.QUESTION_OPTION_COUNTED_ANSWERS));
        }

        correctOptionUUID = question.getCorrectOption().getUuid();

        playerArray = new JSONArray();
        for (Player player : players) {
            JSONObject playerObject = new JSONObject();

            playerObject.put("playerUUID", player.getUuid());
            Answer answer = game.findAnswer(player);
            if (answer != null) {
                playerObject.put("isCorrectAnswer", question.getCorrectOption().getUuid().toString().equals(answer.getAnswerValue()));
            }

            playerArray.put(playerObject);
        }
    }

    public UUID getQuestionUUID() {
        return questionUUID;
    }

    public void setQuestionUUID(UUID questionUUID) {
        this.questionUUID = questionUUID;
    }

    public JSONArray getOptions() {
        return options;
    }

    public void setOptions(JSONArray options) {
        this.options = options;
    }

    public JSONArray getPlayerArray() {
        return playerArray;
    }

    public void setPlayerArray(JSONArray playerArray) {
        this.playerArray = playerArray;
    }

    public UUID getCorrectOptionUUID() {
        return correctOptionUUID;
    }

    public void setCorrectOptionUUID(UUID correctOptionUUID) {
        this.correctOptionUUID = correctOptionUUID;
    }
}
