package requests.spark.websockets.objects.messages.request;

import clarity.Clarity;
import clarity.load.store.expression.Formula;
import log.AppLogger;
import org.apache.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataobjects.CalculateSumData;
import requests.spark.websockets.objects.messages.mapping.WSData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

@MessageType("CalculateSum")
@WebSocketDataClass(CalculateSumData.class)
public class CalculateSum extends Message {
    private static Logger log = AppLogger.logger();

    public void process() {
        CalculateSumData calculateSumData = (CalculateSumData) this.getWebSocketData();
        Formula clarityFormula = Clarity.formula(calculateSumData.getFormula());

        game.actors.Formula formula = new game.actors.Formula();

        formula.convertClarityNode(clarityFormula.getRoot());


//        JSONArray allScores = new JSONArray();
//        for (Score score : currentGame.getScores()) {
//            allScores.put(score.prepareForJSON(WSData.SCORE_SCORE, WSData.SCORE_PLAYER));
//        }

        formula.getRootNode().prepareForJSON();

        calculateSumData.setResult(Double.parseDouble(clarityFormula.solve().toString()));
        calculateSumData.setNode(formula.getRootNode());
    }
}
