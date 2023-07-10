package requests.spark.websockets.objects.messages.request;

import clarity.definition.FormulaContext;
import data.model.dao.FormulaContextDAO;
import log.AppLogger;
import org.apache.logging.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataitems.FormulaContextDataItem;
import requests.spark.websockets.objects.messages.dataobjects.RequestFormulaContextsData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

import java.util.ArrayList;
import java.util.List;

@MessageType("RequestFormulaContexts")
@WebSocketDataClass(RequestFormulaContextsData.class)
public class RequestFormulaContexts extends Message {
    private static Logger log = AppLogger.logger();

    public void process() {
        RequestFormulaContextsData requestFormulaContextData = (RequestFormulaContextsData) this.getWebSocketData();

        List<FormulaContextDataItem> formulaContextDataItems = new ArrayList<>();
        for (FormulaContext formulaContext : new FormulaContextDAO().getAllFormulaContexts()) {
            formulaContextDataItems.add(new FormulaContextDataItem(formulaContext));
        }

        requestFormulaContextData.setFormulaContexts(formulaContextDataItems);
    }
}
