package requests.spark.websockets.objects.messages.push;

import org.apache.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;

@MessageType("AnswerUpdate")
public class AnswerUpdate extends Message {
    private static Logger log = Logger.getLogger(AnswerUpdate.class);
}
