package requests.spark.websockets.objects.messages.request;

import clarity.Entry;
import clarity.Infer;
import clarity.Record;
import clarity.load.store.expression.Formula;
import clarity.load.store.expression.instance.InstancedFormula;
import log.AppLogger;
import org.apache.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataobjects.FormulaCheckData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

@MessageType("FormulaCheck")
@WebSocketDataClass(FormulaCheckData.class)
public class FormulaCheck extends Message {
    private static Logger log = AppLogger.logger();

    public void process() {
        FormulaCheckData formulaCheckData = (FormulaCheckData) this.getWebSocketData();

        Record record = new Record("Employee");
        record.set(Entry.create("A", "aLExAnder"));
        record.set(Entry.create("B", "bROwn"));

        Infer.infer();

        Entry entry = record.get(formulaCheckData.getFormulaToCheck());

        if (entry != null) {
            formulaCheckData.setResult(entry.get().getValue().toString());

            Formula clarityFormula = entry.getDefinition().getFormula();
            InstancedFormula instancedFormula = clarityFormula
                    .createInstance()
                    .record(entry.getRecord());
            instancedFormula.solve();

            // Convert to actor formula
            game.actors.Formula formula = new game.actors.Formula();
            formula.convertClarityNode(instancedFormula);
            formulaCheckData.setFormula(formula);
        }
    }
}
