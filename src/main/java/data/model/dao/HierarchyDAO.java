package data.model.dao;

import clarity.definition.Hierarchy;
import data.SelectQuery;
import data.SelectResult;
import data.SelectResultRow;
import log.AppLogger;
import org.apache.logging.log4j.Logger;

public class HierarchyDAO {
    private static Logger log = AppLogger.logger();

    public HierarchyDAO() {
    }

    public Hierarchy getNodeByReference(String nodeReference) {
        SelectResult selectResult = (SelectResult) new SelectQuery("select uuid from hierarchy where node_reference = ?")
                .addParameter(nodeReference) // 1
                .execute();

        for (SelectResultRow resultRow : selectResult.getResults()) { // Single result
            String uuid = resultRow.getString("uuid");
            return Hierarchy.load(DAO.UUIDFromString(uuid), Hierarchy.class);
        }
        return null;
    }
}
