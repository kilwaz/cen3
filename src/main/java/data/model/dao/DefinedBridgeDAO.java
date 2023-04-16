package data.model.dao;


import clarity.load.excel.DefinedBridge;
import clarity.load.excel.DefinedTemplate;
import data.SelectQuery;
import data.SelectResult;
import data.SelectResultRow;
import log.AppLogger;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class DefinedBridgeDAO {
    private static Logger log = AppLogger.logger();

    public DefinedBridgeDAO() {
    }

    public List<DefinedBridge> getDefinedBridgesByDefinedTemplate(DefinedTemplate definedTemplate) {
        SelectResult selectResult = (SelectResult) new SelectQuery("select uuid from defined_bridge where defined_template_id = ?")
                .addParameter(definedTemplate.getUuidString()) // 1
                .execute();
        return processQueryResults(selectResult);
    }

    private List<DefinedBridge> processQueryResults(SelectResult selectResult) {
        List<DefinedBridge> definedBridges = new ArrayList<>();
        for (SelectResultRow resultRow : selectResult.getResults()) {
            DefinedBridge definedBridge = DefinedBridge.load(DAO.UUIDFromString(resultRow.getString("uuid")), DefinedBridge.class);
            definedBridges.add(definedBridge);
        }
        return definedBridges;
    }
}
