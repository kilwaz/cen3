package requests.spark.websockets.objects.messages.outgoing;

import game.Game;
import game.GameManager;
import game.actors.Answer;
import game.actors.Player;
import game.actors.Question;
import game.actors.QuestionOption;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;

import java.util.ArrayList;
import java.util.List;

@MessageType("QuestionResults")
public class QuestionResults extends Message {
    private static Logger log = Logger.getLogger(QuestionResults.class);

    private Question question = null;
    private List<Player> players = new ArrayList<>();

    public QuestionResults question(Question question) {
        this.question = question;
        return this;
    }

    public QuestionResults players(List<Player> players) {
        this.players = new ArrayList<>(players);
        return this;
    }

    public void prepareToSend() {
        Game game = GameManager.getInstance().getCurrentGame();
        addResponseData("questionUUID", question.getUuid());

        JSONArray options = new JSONArray();
        for (QuestionOption questionOption : question.getPossibleOptions()) {
            JSONObject option = new JSONObject();

            option.put("optionUUID", questionOption.getUuid());
            option.put("optionAnswerCount", questionOption.getCountedAnswers());
            option.put("isCorrectAnswer", question.getCorrectOption().equals(questionOption));
            options.put(option);
        }

        addResponseData("options", options);
        addResponseData("correctOptionUUID", question.getCorrectOption().getUuid());

        JSONArray playerArray = new JSONArray();
        for (Player player : players) {
            JSONObject playerObject = new JSONObject();

            playerObject.put("playerUUID", player.getUuid());
            Answer answer = game.findAnswer(player);
            if (answer != null) {
                playerObject.put("isCorrectAnswer", question.getCorrectOption().getUuid().toString().equals(answer.getAnswerValue()));
            }

            playerArray.put(playerObject);
        }

        addResponseData("playerResults", playerArray);
    }
}
