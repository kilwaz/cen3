package data.model.dao;


import clarity.definition.RecordDefinition;
import data.SelectQuery;
import data.SelectResult;
import data.SelectResultRow;
import log.AppLogger;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class RecordDefinitionDAO {
    private static Logger log = AppLogger.logger();

    public RecordDefinitionDAO() {
    }

    public RecordDefinition getRecordDefinitionByName(String name) {
        RecordDefinition recordDefinition = null;

        SelectResult selectResult = (SelectResult) new SelectQuery("select uuid from record_definition where name = ?")
                .addParameter(name)
                .execute();

        for (SelectResultRow resultRow : selectResult.getResults()) {
            String uuid = resultRow.getString("uuid");
            recordDefinition = RecordDefinition.load(DAO.UUIDFromString(uuid), RecordDefinition.class);
        }

        return recordDefinition;
    }

    public List<RecordDefinition> getAllRecordDefinitions() {
        List<RecordDefinition> recordDefinitions = new ArrayList<>();

        SelectResult selectResult = (SelectResult) new SelectQuery("select uuid from record_definition").execute();

        for (SelectResultRow resultRow : selectResult.getResults()) {
            String uuid = resultRow.getString("uuid");
            recordDefinitions.add(RecordDefinition.load(DAO.UUIDFromString(uuid), RecordDefinition.class));
        }

        return recordDefinitions;
    }
}
