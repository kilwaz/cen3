package requests.spark.websockets.objects.messages.request;

import game.Game;
import game.GameManager;
import game.actors.Question;
import org.apache.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.WebSocketAction;
import requests.spark.websockets.objects.messages.dataobjects.NewQuestionData;
import requests.spark.websockets.objects.messages.dataobjects.NextQuestionData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;
import requests.spark.websockets.objects.messages.push.NextQuestion;

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
        Message.push(NextQuestion.class, new NextQuestionData(question), WebSocketAction.ALL_PLAYERS);
        Message.push(NextQuestion.class, new NextQuestionData(question), WebSocketAction.ALL_ADMINS);

        newQuestionData.setQuestionText(question.getQuestionText());
    }
}
