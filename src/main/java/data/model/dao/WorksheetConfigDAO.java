package data.model.dao;

import clarity.definition.WorksheetConfig;
import data.SelectQuery;
import data.SelectResult;
import data.SelectResultRow;

import java.util.ArrayList;
import java.util.List;

public class WorksheetConfigDAO {
    public WorksheetConfigDAO() {
    }

    public WorksheetConfig getWorksheetConfig(String name) {
        WorksheetConfig worksheetConfig = null;

        SelectResult selectResult = (SelectResult) new SelectQuery("select uuid from worksheet_config where name = ?")
                .addParameter(name)
                .execute();

        for (SelectResultRow resultRow : selectResult.getResults()) {
            String uuid = resultRow.getString("uuid");

            worksheetConfig = WorksheetConfig.load(DAO.UUIDFromString(uuid), WorksheetConfig.class);
        }

        return worksheetConfig;
    }

    public List<WorksheetConfig> getAllWorksheetConfigs() {
        List<WorksheetConfig> worksheetConfigs = new ArrayList<>();

        SelectResult selectResult = (SelectResult) new SelectQuery("select uuid from worksheet_config")
                .execute();

        for (SelectResultRow resultRow : selectResult.getResults()) {
            String uuid = resultRow.getString("uuid");
            worksheetConfigs.add(WorksheetConfig.load(DAO.UUIDFromString(uuid), WorksheetConfig.class));
        }

        return worksheetConfigs;
    }
}