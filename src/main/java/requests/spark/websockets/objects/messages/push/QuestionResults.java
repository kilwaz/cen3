package requests.spark.websockets.objects.messages.push;

import org.apache.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataobjects.QuestionResultsData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

@MessageType("QuestionResults")
@WebSocketDataClass(QuestionResultsData.class)
public class QuestionResults extends Message {
    private static Logger log = Logger.getLogger(QuestionResults.class);
}
