package requests.spark.websockets.objects.messages.request;

import clarity.Record;
import clarity.definition.Definitions;
import clarity.definition.RecordState;
import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Formula;
import clarity.load.store.expression.instance.InstancedFormula;
import data.model.DatabaseCollect;
import log.AppLogger;
import org.apache.logging.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataobjects.SummaryData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

import java.util.List;

@MessageType("Summary")
@WebSocketDataClass(SummaryData.class)
public class Summary extends Message {
    private static Logger log = AppLogger.logger();

    public void process() {
        SummaryData summaryData = (SummaryData) this.getWebSocketData();
        summaryData.setContent("Woop");

        List<Record> empRecords = DatabaseCollect
                .create()
                .recordDefinition(Definitions.getInstance().getRecordDefinition("Employee"))
                .state(RecordState.STATIC)
                .nodeReference(summaryData.getRequestID())
                .collect();

        long count = System.currentTimeMillis();

//        Formula formula = new Formula("sumAgg([New_Salary]+1000)");
//        Formula formula = new Formula("if(true,1,0)");
        Formula formula = new Formula("sumAgg(if(false,1,0))");
//        Formula formula = new Formula("sum(3,1,0)");
//        Formula formula = new Formula("sumAgg(if('Grade 4'=[Grade],1,0))");
        formula.build();

//        InstancedFormula instancedFormula = formula.createInstance();
        InstancedFormula instancedFormula = formula.createAggInstance();
        instancedFormula.records(empRecords);

        Expression result = instancedFormula.solve();

        log.info("End result => " + result.getStringRepresentation() + " in " + (System.currentTimeMillis() - count));
    }
}
