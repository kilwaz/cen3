package requests.spark.websockets.objects.messages.request;

import log.AppLogger;
import org.apache.log4j.Logger;
import org.mariuszgromada.math.mxparser.Expression;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataobjects.CalculateSumData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

@MessageType("CalculateSum")
@WebSocketDataClass(CalculateSumData.class)
public class CalculateSum extends Message {
    private static Logger log = AppLogger.logger();

    public void process() {
        CalculateSumData calculateSumData = (CalculateSumData) this.getWebSocketData();
        Expression e = new Expression(calculateSumData.getFormula());
        calculateSumData.setResult(e.calculate());
        log.info("Calculated");
    }
}
