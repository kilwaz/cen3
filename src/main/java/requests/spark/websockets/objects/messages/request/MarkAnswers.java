package requests.spark.websockets.objects.messages.request;

import game.Game;
import game.GameManager;
import game.actors.Question;
import org.apache.log4j.Logger;
import requests.spark.websockets.objects.*;
import requests.spark.websockets.objects.messages.dataobjects.MarkAnswersData;
import requests.spark.websockets.objects.messages.dataobjects.QuestionResultsData;
import requests.spark.websockets.objects.messages.dataobjects.UpdateScoreData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

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
            Push.message(PushMessage.QUESTION_RESULTS)
                    .data(new QuestionResultsData(markedQuestion, currentGame.getPlayers()))
                    .to(Audience.ALL_ADMINS)
                    .push();
        }

        Push.message(PushMessage.UPDATE_SCORE)
                .data(new UpdateScoreData(currentGame.getScores()))
                .to(Audience.ALL_ADMINS)
                .push();
        currentGame.clearAnswers();
    }
}
