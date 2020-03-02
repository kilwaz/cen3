package clarity.definition;

import data.SelectQuery;
import data.SelectResult;
import log.AppLogger;
import org.apache.log4j.Logger;

import java.util.HashMap;

public class DefinitionTableModel {
    private static Logger log = AppLogger.logger();

    private HashMap<String, DatabaseColumnModel> databaseColumnModelHashMap = new HashMap<>();
    private HashMap<String, DatabaseColumnModel> definedModelHashMap = new HashMap<>();

    private Boolean databaseTableExists;
    private Boolean definedTableExists;

    private RecordDefinition recordDefinition;

    public DefinitionTableModel(RecordDefinition recordDefinition) {
        this.recordDefinition = recordDefinition;
    }

    public void update() {
        definedTableExists = recordDefinition == null;
        var definitionHashMap = recordDefinition.getDefinitionHashMap();

        definedModelHashMap.clear();

        for (Definition definition : definitionHashMap.values()) {
            definedModelHashMap.put(definition.getName(), new DatabaseColumnModel(definition.getName(), "varchar"));
        }
    }

    public void verifyTable() {
        if (recordDefinition != null) {
            var selectResult = (SelectResult) new SelectQuery("describe " + recordDefinition.getName().toLowerCase()).execute();
            if (selectResult.hasException()) { // Most likely the table doesn't exist
                Exception sqlException = selectResult.getException();
                log.info("There was an SQL exception with our query " + sqlException.getMessage());
            } else {
                databaseTableExists = true; // If no error then this is true
                for (var resultRow : selectResult.getResults()) {
                    String columnName = resultRow.getString("COLUMN_NAME");
                    String columnType = resultRow.getString("COLUMN_TYPE");
                    log.info(columnName + " - " + columnType);
                }
            }
        }
    }

    public Boolean getDefinedTableExists() {
        return definedTableExists;
    }

    public DefinitionTableModel definedTableExists(Boolean definedTableExists) {
        this.definedTableExists = definedTableExists;
        return this;
    }

    public String getDeltaQuery() {
        return "";
    }
}
