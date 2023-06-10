package data.model;

import clarity.definition.HierarchyNode;
import data.SelectQuery;
import data.SelectResult;
import log.AppLogger;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HierarchyCollect {
    private static Logger log = AppLogger.logger();

    private HierarchyCollect() {

    }

    public static HierarchyCollect create() {
        return new HierarchyCollect();
    }

    public List<HierarchyNode> collect() {
        List<HierarchyNode> hierarchyNodes = new ArrayList<>();

        // Building the select query string
        var selectQueryBuilder = new StringBuilder();
        selectQueryBuilder.append("select uuid from hierarchy_nodes where node_type = 'Node'");
//        if (primaryKey != null) {
//            selectQueryBuilder.append(" where ").append(recordDefinition.getPrimaryKey().getName()).append(" = '").append(primaryKey).append("'");
//        }

        log.info("Hierarchy query vvv");
        log.info(selectQueryBuilder);

        var selectResult = (SelectResult) new SelectQuery(selectQueryBuilder.toString()).execute();
        for (var resultRow : selectResult.getResults()) {
            String uuid = resultRow.getString("UUID");
            HierarchyNode loadedNode = HierarchyNode.load(UUID.fromString(uuid), HierarchyNode.class);
            hierarchyNodes.add(loadedNode);
        }

        return hierarchyNodes;
    }
}