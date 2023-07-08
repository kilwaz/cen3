package data.model.dao;

import clarity.definition.WorksheetConfig;
import clarity.definition.WorksheetConfigDetails;
import data.SelectQuery;
import data.SelectResult;
import data.SelectResultRow;
import log.AppLogger;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class WorksheetConfigDetailsDAO {
    private static Logger log = AppLogger.logger();

    public WorksheetConfigDetailsDAO() {
    }

    public List<WorksheetConfigDetails> getWorksheetConfigDetails(WorksheetConfig worksheetConfig) {
        List<WorksheetConfigDetails> worksheetConfigs = new ArrayList<>();

        SelectResult selectResult = (SelectResult) new SelectQuery("select uuid from worksheet_config_details where worksheet_config_id = ? order by column_order")
                .addParameter(worksheetConfig.getUuidString())
                .execute();

        for (SelectResultRow resultRow : selectResult.getResults()) {
            String uuid = resultRow.getString("uuid");
            worksheetConfigs.add(WorksheetConfigDetails.load(DAO.UUIDFromString(uuid), WorksheetConfigDetails.class));
        }

        return worksheetConfigs;
    }
}
