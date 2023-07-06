package requests.spark.websockets.objects.messages.request;

import clarity.Record;
import clarity.definition.*;
import clarity.load.store.expression.Formula;
import clarity.load.store.expression.instance.InstancedFormulaView;
import data.model.DatabaseCollect;
import data.model.dao.FormulaContextDAO;
import data.model.dao.FormulaContextGroupDAO;
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


        FormulaContextDAO formulaContextDAO = new FormulaContextDAO();
        FormulaContext formulaContext = formulaContextDAO.getFormulaContextName("Summary");

        FormulaContextGroupDAO formulaContextGroupDAO = new FormulaContextGroupDAO();
        List<FormulaContextGroup> formulaContextGroups = formulaContextGroupDAO.getFormulaContextGroupByFormulaContext(formulaContext);

        FormulaView formulaView = new FormulaView();
        for (FormulaContextGroup formulaContextGroup : formulaContextGroups) {
            if (Definition.CONTEXT_TYPE_VIEW.equals(formulaContextGroup.getDefinition().getContextType())) {
                formulaView.add(formulaContextGroup.getDefinition().getName(), Formula.createForFormulaView(formulaContextGroup.getDefinition().getExpression()));
            } else if (Definition.CONTEXT_TYPE_AGGREGATE.equals(formulaContextGroup.getDefinition().getContextType())) {
                formulaView.add(formulaContextGroup.getDefinition().getName(), Formula.create(formulaContextGroup.getDefinition().getExpression()));
            }
        }

        InstancedFormulaView instancedFormulaView = formulaView.createInstance();
        instancedFormulaView.records(empRecords);
        instancedFormulaView.solve();
        log.info("Result = " + instancedFormulaView.get("Total").getStringRepresentation());
    }
}
