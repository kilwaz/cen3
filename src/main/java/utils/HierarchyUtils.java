package utils;

import clarity.definition.HierarchyTree;
import data.ProcedureQuery;
import data.model.dao.HierarchyTreeDAO;
import log.AppLogger;
import org.apache.logging.log4j.Logger;

public class HierarchyUtils {
    private static Logger log = AppLogger.logger();

    public static void recalculate() {
        log.info("Starting hierarchy recalculate...");
        HierarchyTreeDAO hierarchyTreeDAO = new HierarchyTreeDAO();
        HierarchyTree hierarchyTree = hierarchyTreeDAO.getHierarchyTreeByName("ARUP");

        ProcedureQuery procedureQuery = new ProcedureQuery("{ call tree_populate(?)}");
        procedureQuery.addParameter(hierarchyTree.getUuidString());
        procedureQuery.execute();
        log.info("Finished hierarchy recalculate");
    }
}