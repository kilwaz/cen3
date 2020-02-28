package data.model.dao;


import clarity.definition.DefinitionGroup;
import clarity.definition.RecordDefinition;
import data.SelectQuery;
import data.SelectResult;
import data.SelectResultRow;
import log.AppLogger;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class DefinitionGroupDAO {
    private static Logger log = AppLogger.logger();

    public DefinitionGroupDAO() {
    }

    public List<DefinitionGroup> getDefinitionGroupByRecordDefinition(RecordDefinition recordDefinition) {
        SelectResult selectResult = (SelectResult) new SelectQuery("select uuid from definition_group where record_definition_id = ?")
                .addParameter(recordDefinition.getUuidString()) // 1
                .execute();
        return processQueryResults(selectResult);
    }

    private List<DefinitionGroup> processQueryResults(SelectResult selectResult) {
        List<DefinitionGroup> definitionGroups = new ArrayList<>();
        for (SelectResultRow resultRow : selectResult.getResults()) {
            DefinitionGroup definitionGroup = DefinitionGroup.load(DAO.UUIDFromString(resultRow.getString("uuid")), DefinitionGroup.class);
            definitionGroups.add(definitionGroup);
        }
        return definitionGroups;
    }
}
