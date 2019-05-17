package requests.spark.websockets.objects.messages.incoming;

import game.Game;
import game.GameManager;
import game.actors.Question;
import org.apache.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.outgoing.NextQuestion;
import requests.spark.websockets.objects.messages.outgoing.UpdateScore;

// This is sent by the game master to trigger the next question
@MessageType("NewQuestion")
public class NewQuestion extends Message {
    private static Logger log = Logger.getLogger(NewQuestion.class);

    public void process() {
        Game currentGame = GameManager.getInstance().getCurrentGame();
        currentGame.markAnswers();

        Question question = currentGame.getNextQuestion();

        // Send off push request to listeners
        NextQuestion nextQuestion = Message.create(NextQuestion.class);
        nextQuestion.nextQuestion(question);
        nextQuestion.sendTo(Message.ALL_PLAYERS);

        NextQuestion nextQuestionAdmin = Message.create(NextQuestion.class);
        nextQuestionAdmin.nextQuestion(question);
        nextQuestionAdmin.sendTo(Message.ALL_ADMINS);

        addResponseData("questionText", question.getQuestionText());

        UpdateScore updateScore = Message.create(UpdateScore.class);
        updateScore.scores(currentGame.getScores());
        updateScore.sendTo(Message.ALL_ADMINS);

        handleResponse();
    }
}
