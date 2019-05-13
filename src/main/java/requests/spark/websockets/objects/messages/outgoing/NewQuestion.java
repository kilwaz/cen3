package requests.spark.websockets.objects.messages.outgoing;

import game.actors.Question;
import game.actors.QuestionOption;
import org.apache.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.incoming.NextQuestion;

// This is sent by the admin to trigger the next question
@MessageType("NewQuestion")
public class NewQuestion extends Message {
    private static Logger log = Logger.getLogger(NewQuestion.class);

    public void process() {
        log.info("REQUEST TO SEND NEXT QUESTION!");

        QuestionOption answer1 = new QuestionOption().answerValue("Correct Answer 1");
        QuestionOption answer2 = new QuestionOption().answerValue("Wrong Answer 2");
        QuestionOption answer3 = new QuestionOption().answerValue("Wrong Answer 3");
        QuestionOption answer4 = new QuestionOption().answerValue("Wrong Answer 4");

        Question question = new Question()
                .correctOption(answer1)
                .wrongOption(answer2)
                .wrongOption(answer3)
                .wrongOption(answer4);

        // Send off push request to listeners
        NextQuestion nextQuestion = Message.create(NextQuestion.class);
        nextQuestion.nextQuestion(question);
        nextQuestion.sendTo(Message.ALL_PLAYERS);

        handleResponse();
    }

    public void prepareToSend() {

    }
}
