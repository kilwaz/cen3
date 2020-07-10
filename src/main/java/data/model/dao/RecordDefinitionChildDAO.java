package data.model.dao;


import clarity.definition.RecordDefinitionChild;
import data.SelectQuery;
import data.SelectResult;
import data.SelectResultRow;
import log.AppLogger;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class RecordDefinitionChildDAO {
    private static Logger log = AppLogger.logger();

    public RecordDefinitionChildDAO() {
    }

    public List<RecordDefinitionChild> getAllRecordDefinitionChildren() {
        List<RecordDefinitionChild> recordDefinitionChildren = new ArrayList<>();

        SelectResult selectResult = (SelectResult) new SelectQuery("select uuid from record_definition_child").execute();

        for (SelectResultRow resultRow : selectResult.getResults()) {
            String uuid = resultRow.getString("uuid");
            recordDefinitionChildren.add(RecordDefinitionChild.load(DAO.UUIDFromString(uuid), RecordDefinitionChild.class));
        }

        return recordDefinitionChildren;
    }
}
