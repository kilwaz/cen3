package requests.spark.websockets.objects.messages.request;

import game.Game;
import game.GameManager;
import game.actors.Question;
import org.apache.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.WebSocketAction;
import requests.spark.websockets.objects.messages.dataobjects.MarkAnswersData;
import requests.spark.websockets.objects.messages.dataobjects.QuestionResultsData;
import requests.spark.websockets.objects.messages.dataobjects.UpdateScoreData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;
import requests.spark.websockets.objects.messages.push.QuestionResults;
import requests.spark.websockets.objects.messages.push.UpdateScore;

@MessageType("MarkAnswers")
@WebSocketDataClass(MarkAnswersData.class)
public class MarkAnswers extends Message {
    private static Logger log = Logger.getLogger(MarkAnswers.class);

    public void process() {
        MarkAnswersData markAnswersData = (MarkAnswersData) this.getWebSocketData();

        Game currentGame = GameManager.getInstance().getCurrentGame();
        currentGame.markAnswers();

        Question markedQuestion = currentGame.getCurrentQuestion();

        if (markedQuestion != null) {
            Message.push(QuestionResults.class, new QuestionResultsData(markedQuestion, currentGame.getPlayers()), WebSocketAction.ALL_ADMINS);
        }

        Message.push(UpdateScore.class, new UpdateScoreData(currentGame.getScores()), WebSocketAction.ALL_ADMINS);
        currentGame.clearAnswers();
    }
}
