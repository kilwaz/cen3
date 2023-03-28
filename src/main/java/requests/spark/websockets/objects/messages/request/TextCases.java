package requests.spark.websockets.objects.messages.request;

import log.AppLogger;
import org.apache.logging.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataobjects.TextCasesData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

import java.util.Locale;

@MessageType("TextCases")
@WebSocketDataClass(TextCasesData.class)
public class TextCases extends Message {
    private static Logger log = AppLogger.logger();

    public void process() {
        TextCasesData textCasesData = (TextCasesData) this.getWebSocketData();

        String textToProcess = textCasesData.getTextToProcess();
        Integer textFunction = textCasesData.getTextFunction();

        switch (textFunction) {
            case 2:
                textCasesData.setTextResult(textToProcess.toLowerCase(Locale.ROOT));
                break;
            case 3:
                textCasesData.setTextResult(textToProcess.toUpperCase(Locale.ROOT));
                break;

            default:
                textCasesData.setTextResult(textToProcess);
        }
    }
}

