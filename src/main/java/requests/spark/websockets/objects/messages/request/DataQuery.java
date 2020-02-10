package requests.spark.websockets.objects.messages.request;

import log.AppLogger;
import org.apache.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataobjects.DataQueryData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

@MessageType("DataQuery")
@WebSocketDataClass(DataQueryData.class)
public class DataQuery extends Message {
    private static Logger log = AppLogger.logger();

    public void process() {
        DataQueryData dataQueryData = (DataQueryData) this.getWebSocketData();

        dataQueryData.setResult("This is the data coming from here");
    }
}
