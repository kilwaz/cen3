package requests.spark.websockets.objects.messages.request;

import game.Game;
import game.GameManager;
import game.actors.Question;
import org.apache.log4j.Logger;
import requests.spark.websockets.objects.*;
import requests.spark.websockets.objects.messages.dataobjects.NewQuestionData;
import requests.spark.websockets.objects.messages.dataobjects.NextQuestionData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

// This is sent by the game master to trigger the next question
@MessageType("NewQuestion")
@WebSocketDataClass(NewQuestionData.class)
public class NewQuestion extends Message {
    private static Logger log = Logger.getLogger(NewQuestion.class);

    public void process() {
        NewQuestionData newQuestionData = (NewQuestionData) this.getWebSocketData();

        Game currentGame = GameManager.getInstance().getCurrentGame();
        Question question = currentGame.getNextQuestion();

        // Send off push request to listeners
        Push.message(PushMessage.NEXT_QUESTION)
                .data(new NextQuestionData(question))
                .to(Audience.ALL_ADMINS, Audience.ALL_PLAYERS)
                .push();

        newQuestionData.setQuestionText(question.getQuestionText());
    }
}
