package data.model.dao;

import clarity.definition.WorksheetConfig;
import data.SelectQuery;
import data.SelectResult;
import data.SelectResultRow;
import log.AppLogger;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class WorksheetConfigDAO {
    private static Logger log = AppLogger.logger();

    public WorksheetConfigDAO() {
    }

    public List<WorksheetConfig> getAllWorksheetConfigs() {
        List<WorksheetConfig> worksheetConfigs = new ArrayList<>();

        SelectResult selectResult = (SelectResult) new SelectQuery("select uuid from worksheet_config order by column_order").execute();

        for (SelectResultRow resultRow : selectResult.getResults()) {
            String uuid = resultRow.getString("uuid");
            worksheetConfigs.add(WorksheetConfig.load(DAO.UUIDFromString(uuid), WorksheetConfig.class));
        }

        return worksheetConfigs;
    }
}
