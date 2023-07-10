package requests.spark.websockets.objects.messages.request;

import clarity.definition.Definition;
import clarity.definition.FormulaContext;
import clarity.definition.FormulaContextGroup;
import clarity.definition.RecordDefinition;
import data.model.dao.FormulaContextDAO;
import data.model.dao.FormulaContextGroupDAO;
import log.AppLogger;
import org.apache.logging.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataitems.DefinitionDataItem;
import requests.spark.websockets.objects.messages.dataobjects.RequestDefinitionsData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

import java.util.ArrayList;
import java.util.List;

@MessageType("RequestDefinitions")
@WebSocketDataClass(RequestDefinitionsData.class)
public class RequestDefinitions extends Message {
    private static Logger log = AppLogger.logger();

    public void process() {
        RequestDefinitionsData definitionsData = (RequestDefinitionsData) this.getWebSocketData();

        List<DefinitionDataItem> definitionDataItems = new ArrayList<>();
        if (definitionsData.getRequestedRecordDefinitionName() != null) {
            if ("All".equals(definitionsData.getRequestedRecordDefinitionName())) {
                List<Definition> allDefinitions = clarity.definition.Definitions.getInstance().getAllDefinitions();
                for (Definition definition : allDefinitions) {
                    definitionDataItems.add(new DefinitionDataItem(definition));
                }
            } else {
                RecordDefinition recordDefinition = clarity.definition.Definitions.getInstance().getRecordDefinition(definitionsData.getRequestedRecordDefinitionName());
                for (Definition definition : recordDefinition.getDefinitions()) {
                    definitionDataItems.add(new DefinitionDataItem(definition));
                }
            }
        } else {
            FormulaContextDAO formulaContextDAO = new FormulaContextDAO();
            FormulaContext formulaContext = formulaContextDAO.getFormulaContextName(definitionsData.getRequestedFormulaContextName());

            FormulaContextGroupDAO formulaContextGroupDAO = new FormulaContextGroupDAO();
            List<FormulaContextGroup> formulaContextGroups = formulaContextGroupDAO.getFormulaContextGroupByFormulaContext(formulaContext);

            for (FormulaContextGroup formulaContextGroup : formulaContextGroups) {
                definitionDataItems.add(new DefinitionDataItem(formulaContextGroup.getDefinition()));
            }
        }

        definitionsData.setDefinitions(definitionDataItems);
    }
}
