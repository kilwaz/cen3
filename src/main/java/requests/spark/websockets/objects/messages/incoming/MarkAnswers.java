package requests.spark.websockets.objects.messages.incoming;

import game.Game;
import game.GameManager;
import game.actors.Question;
import org.apache.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.outgoing.QuestionResults;
import requests.spark.websockets.objects.messages.outgoing.UpdateScore;

@MessageType("MarkAnswers")
public class MarkAnswers extends Message {
    private static Logger log = Logger.getLogger(MarkAnswers.class);

    public void process() {
        Game currentGame = GameManager.getInstance().getCurrentGame();
        currentGame.markAnswers();

        Question markedQuestion = currentGame.getCurrentQuestion();

        if (markedQuestion != null) {
            QuestionResults questionResults = Message.create(QuestionResults.class);
            questionResults.question(markedQuestion);
            questionResults.players(currentGame.getPlayers());
            questionResults.sendTo(Message.ALL_ADMINS);
        }

        UpdateScore updateScore = Message.create(UpdateScore.class);
        updateScore.scores(currentGame.getScores());
        updateScore.sendTo(Message.ALL_ADMINS);

        currentGame.clearAnswers();

        handleResponse();
    }
}
