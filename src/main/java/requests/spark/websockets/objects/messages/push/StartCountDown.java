package requests.spark.websockets.objects.messages.push;

import org.apache.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataobjects.StartCountDownData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;


// This is sent by the server to the players with details of the next question
@MessageType("StartCountDown")
@WebSocketDataClass(StartCountDownData.class)
public class StartCountDown extends Message {
    private static Logger log = Logger.getLogger(StartCountDown.class);
}
