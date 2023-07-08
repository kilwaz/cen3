package requests.spark.websockets.objects.messages.request;

import clarity.Record;
import clarity.definition.*;
import clarity.load.store.expression.Formula;
import clarity.load.store.expression.instance.InstancedFormulaView;
import data.model.DatabaseCollect;
import data.model.dao.FormulaContextDAO;
import data.model.dao.FormulaContextGroupDAO;
import data.model.dao.WorksheetConfigDAO;
import data.model.dao.WorksheetConfigDetailsDAO;
import log.AppLogger;
import org.apache.logging.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataitems.ConfigurableUiDataItem;
import requests.spark.websockets.objects.messages.dataitems.WebEntryDataItem;
import requests.spark.websockets.objects.messages.dataitems.WebRecordDataItem;
import requests.spark.websockets.objects.messages.dataobjects.SummaryData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

import java.util.ArrayList;
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
                .nodeReference(summaryData.getNodeReference())
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

        // Need an object that handles this, takes a formula view and some sort of table configuration
        // Specifically defined record list + dynamically defined
        // Flip the table on its side as a flag?
        // Column titles
        // Definitions are used in the FormulaView, to help link headers and records
        // Like worksheet_config
        // ConfigurableUiData

        // Have two different types of table, an 'indicators' table and then a normal records table

        WorksheetConfigDAO worksheetConfigDAO = new WorksheetConfigDAO();
        WorksheetConfig worksheetConfig = worksheetConfigDAO.getWorksheetConfig("Summary");
        List<WorksheetConfigDetails> worksheetConfigDetails = new WorksheetConfigDetailsDAO().getWorksheetConfigDetails(worksheetConfig);

        List<WebRecordDataItem> records = new ArrayList<>();
        for (WorksheetConfigDetails worksheetConfigDetail : worksheetConfigDetails) {
            WebRecordDataItem webRecord = new WebRecordDataItem();
            List<WebEntryDataItem> entries = new ArrayList<>();
            WebEntryDataItem webEntryDataItem = new WebEntryDataItem("Name", worksheetConfigDetail.getColumnTitle());
            WebEntryDataItem webEntryDataItem2 = new WebEntryDataItem("Value", instancedFormulaView.get(worksheetConfigDetail.getDefinition().getName()).getStringRepresentation());
            entries.add(webEntryDataItem);
            entries.add(webEntryDataItem2);
            webRecord.setEntries(entries);

            records.add(webRecord);
        }

        ConfigurableUiDataItem configurableUiDataItem = new ConfigurableUiDataItem("Table", records);
        summaryData.setConfigurableUiDataItems(List.of(configurableUiDataItem));
    }
}
