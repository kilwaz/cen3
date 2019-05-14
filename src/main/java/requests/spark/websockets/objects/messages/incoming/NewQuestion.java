package requests.spark.websockets.objects.messages.incoming;

import game.Game;
import game.GameManager;
import game.actors.Player;
import org.apache.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.outgoing.NextQuestion;
import requests.spark.websockets.objects.messages.outgoing.UpdateScore;

// This is sent by the admin to trigger the next question
@MessageType("NewQuestion")
public class NewQuestion extends Message {
    private static Logger log = Logger.getLogger(NewQuestion.class);

    public void process() {
        log.info("REQUEST TO SEND NEXT QUESTION!");

        Game currentGame = GameManager.getInstance().getCurrentGame();

        currentGame.markAnswers();

        // Send off push request to listeners
        NextQuestion nextQuestion = Message.create(NextQuestion.class);
        nextQuestion.nextQuestion(currentGame.getNextQuestion());
        nextQuestion.sendTo(Message.ALL_PLAYERS);

        for (Player player : currentGame.getPlayers()) {
            UpdateScore updateScore = Message.create(UpdateScore.class);
            updateScore.score(currentGame.getScore(player));
            updateScore.sendTo(Message.ALL_ADMINS);
        }

        handleResponse();
    }

    public void prepareToSend() {

    }
}
