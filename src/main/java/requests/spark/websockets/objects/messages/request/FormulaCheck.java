package requests.spark.websockets.objects.messages.request;

import clarity.Entry;
import clarity.Record;
import clarity.load.store.Records;
import clarity.load.store.expression.Formula;
import clarity.load.store.expression.instance.InstancedFormula;
import log.AppLogger;
import org.apache.logging.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataitems.FormulaDataItem;
import requests.spark.websockets.objects.messages.dataobjects.FormulaCheckData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

import java.util.List;

@MessageType("FormulaCheck")
@WebSocketDataClass(FormulaCheckData.class)
public class FormulaCheck extends Message {
    private static Logger log = AppLogger.logger();

    public void process() {
        FormulaCheckData formulaCheckData = (FormulaCheckData) this.getWebSocketData();
        List<Record> records = Records.getInstance().findRecords("ID", "1");

        if (records.size() > 0) {
            Entry entry = records.get(0).get(formulaCheckData.getFormulaToCheck());

            if (entry != null) {
                formulaCheckData.setResult(entry.get().getValue().toString());

                Formula clarityFormula = entry.getDefinition().getFormula();

                if (clarityFormula == null) {
                    clarityFormula = Formula.create(entry.get().getFormulaSafeValue());
                }

                InstancedFormula instancedFormula = clarityFormula
                        .createInstance()
                        .record(entry.getRecord());
                instancedFormula.solve();

                // Convert to actor formula
                FormulaDataItem formula = new FormulaDataItem();
                formula.convertClarityNode(instancedFormula);
                formulaCheckData.setFormula(formula);
            }
        }
    }
}
