package data.model;

import clarity.Record;
import clarity.definition.RecordDefinition;
import data.SelectQuery;
import data.SelectResult;
import log.AppLogger;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DatabaseCollect {
    private static Logger log = AppLogger.logger();

    private RecordDefinition recordDefinition;
    private int state;
    private String primaryKey;

    private DatabaseCollect() {

    }

    public static DatabaseCollect create() {
        return new DatabaseCollect();
    }

    public DatabaseCollect recordDefinition(RecordDefinition recordDefinition) {
        this.recordDefinition = recordDefinition;
        return this;
    }

    public DatabaseCollect state(int state) {
        this.state = state;
        return this;
    }

    public DatabaseCollect primaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
        return this;
    }

    public List<Record> collect() {
        if (recordDefinition == null) {
            log.info("Can't collect due to null requirements");
            return null;
        }

        List<Record> resultList = new ArrayList<>();

        // Building the select query string
        var selectQueryBuilder = new StringBuilder();
        selectQueryBuilder.append("select uuid from ").append(recordDefinition.getTableNameByState(state));
        if (primaryKey != null) {
            selectQueryBuilder.append(" where " + recordDefinition.getPrimaryKey().getName() + " = '" + primaryKey + "'");
        }

        var selectResult = (SelectResult) new SelectQuery(selectQueryBuilder.toString()).execute();
        for (var resultRow : selectResult.getResults()) {
            String uuid = resultRow.getString("UUID");
            Record loadedRecord = Record.load(recordDefinition, UUID.fromString(uuid));
            loadedRecord.load(state);
            resultList.add(loadedRecord);
        }

        return resultList;
    }

    public Record singleResult() {
        List<Record> results = collect();

        if (results.size() == 0) {
            return null;
        } else {
            return results.get(0);
        }
    }
}