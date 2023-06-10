package data.model.dao;

import clarity.definition.HierarchyNode;
import data.SelectQuery;
import data.SelectResult;
import data.SelectResultRow;
import log.AppLogger;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class HierarchyNodeDAO {
    private static Logger log = AppLogger.logger();

    public HierarchyNodeDAO() {
    }

    public HierarchyNode getNodeByReference(String nodeReference) {
        SelectResult selectResult = (SelectResult) new SelectQuery("select uuid from hierarchy_nodes where node_reference = ? and node_type = 'Node'")
                .addParameter(nodeReference) // 1
                .execute();

        for (SelectResultRow resultRow : selectResult.getResults()) { // Single result
            String uuid = resultRow.getString("uuid");
            return HierarchyNode.load(DAO.UUIDFromString(uuid), HierarchyNode.class);
        }
        return null;
    }

    public HierarchyNode populateChildren(HierarchyNode hierarchyNode) {
        hierarchyNode.children(new ArrayList<>());

        SelectResult selectResult = (SelectResult) new SelectQuery("select uuid from hierarchy_nodes where parent_reference = ? and node_type = 'Node'")
                .addParameter(hierarchyNode.getNodeReference()) // 1
                .execute();

        for (SelectResultRow resultRow : selectResult.getResults()) {
            String uuid = resultRow.getString("uuid");

            HierarchyNode child = HierarchyNode.load(DAO.UUIDFromString(uuid), HierarchyNode.class);
            populateChildren(child);
            hierarchyNode.addChild(child);
        }

        return hierarchyNode;
    }
}
