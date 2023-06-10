package data.model.dao;

import clarity.definition.HierarchyTree;
import data.SelectQuery;
import data.SelectResult;
import data.SelectResultRow;
import log.AppLogger;
import org.apache.logging.log4j.Logger;

public class HierarchyTreeDAO {
    private static Logger log = AppLogger.logger();

    public HierarchyTreeDAO() {
    }

    public HierarchyTree getHierarchyTreeByName(String name) {
        SelectResult selectResult = (SelectResult) new SelectQuery("select uuid from hierarchy_trees where name = ?")
                .addParameter(name) // 1
                .execute();

        for (SelectResultRow resultRow : selectResult.getResults()) { // Single result
            String uuid = resultRow.getString("uuid");
            return HierarchyTree.load(DAO.UUIDFromString(uuid), HierarchyTree.class);
        }
        return null;
    }
}
