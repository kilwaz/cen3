package requests.spark.websockets.objects.messages.push;

import org.apache.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;

@MessageType("NewPlayerJoined")
public class NewPlayerJoined extends Message {
    private static Logger log = Logger.getLogger(NewPlayerJoined.class);
}
