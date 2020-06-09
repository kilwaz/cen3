package clarity.definition;

import data.SelectQuery;
import data.SelectResult;
import log.AppLogger;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DefinitionTableModel {
    private static Logger log = AppLogger.logger();

    private HashMap<String, DatabaseColumnModel> databaseColumnModelHashMap = new HashMap<>();
    private HashMap<String, DatabaseColumnModel> definedModelHashMap = new HashMap<>();

    private Boolean databaseTableExists;
    private Boolean definedTableExists;

    private static HashMap<Integer, String> databaseTypeMappings = new HashMap<>();

    static {
        databaseTypeMappings.put(Definition.DEFINITION_TYPE_UNDEFINED, "varchar(200)");
        databaseTypeMappings.put(Definition.DEFINITION_TYPE_NUMBER, "int");
        databaseTypeMappings.put(Definition.DEFINITION_TYPE_STRING, "varchar(200)");
        databaseTypeMappings.put(Definition.DEFINITION_TYPE_DURATION, "varchar(200)");
    }

    private RecordDefinition recordDefinition;

    public DefinitionTableModel(RecordDefinition recordDefinition) {
        this.recordDefinition = recordDefinition;
    }

    public void update() {
        definedTableExists = recordDefinition == null;
        var definitionHashMap = recordDefinition.getDefinitionHashMap();

        definedModelHashMap.clear();

        for (Definition definition : definitionHashMap.values()) {
            if (!definition.isCalculated()) {
                definedModelHashMap.put(definition.getName(), new DatabaseColumnModel(definition.getName(), databaseTypeMappings.get(definition.getDefinitionType())));
            }
        }
    }

    public void verifyTable() {
        if (recordDefinition != null) {
            var selectResult = (SelectResult) new SelectQuery("describe " + recordDefinition.getName().toLowerCase()).execute();
            if (selectResult.hasException()) { // Most likely the table doesn't exist
                databaseTableExists = false;
            } else {
                databaseTableExists = true; // If no error then this is true
                for (var resultRow : selectResult.getResults()) {
                    String columnName = "";
                    String columnType = "";

                    //TODO: Why is this different?  Different MySQL/MariaDB versions?
                    if (resultRow.hasColumn("Field")) {
                        columnName = resultRow.getString("Field");
                    } else if (resultRow.hasColumn("COLUMN_NAME")) {
                        columnName = resultRow.getString("COLUMN_NAME");
                    }

                    if (resultRow.hasColumn("Type")) {
                        columnType = resultRow.getString("Type");
                    } else if (resultRow.hasColumn("COLUMN_TYPE")) {
                        columnType = resultRow.getString("COLUMN_TYPE");
                    }

                    log.info(columnName + " - " + columnType);

                    databaseColumnModelHashMap.put(columnName, new DatabaseColumnModel(columnName, columnType));
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

    public List<SelectQuery> getDeltaQueries() {
        List<SelectQuery> queries = new ArrayList<>();
        if (recordDefinition != null) {
            if (databaseTableExists) {
                for (String definedModelKey : definedModelHashMap.keySet()) {
                    DatabaseColumnModel databaseColumn = databaseColumnModelHashMap.get(definedModelKey);
                    DatabaseColumnModel definedColumn = definedModelHashMap.get(definedModelKey);

                    if (databaseColumn == null && definedColumn != null) {
                        queries.add(new SelectQuery("alter table " + recordDefinition.getName().toLowerCase() + " add " + definedModelKey + " " + definedColumn.getColumnType()));
                    } else if (databaseColumn != null && definedColumn == null) {
                        queries.add(new SelectQuery("alter table " + recordDefinition.getName().toLowerCase() + " drop " + definedModelKey));
                    }

                    if (databaseColumn != null && definedColumn != null && !databaseColumn.getColumnType().equals(definedColumn.getColumnType())) {
                        queries.add(new SelectQuery("alter table " + recordDefinition.getName().toLowerCase() + " modify column " + definedColumn.getColumnName() + " " + definedColumn.getColumnType()));
                    }
                }
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("create table ").append(recordDefinition.getName().toLowerCase()).append(" (\n")
                        .append("uuid char(36) NOT NULL,\n");

                for (Definition definition : recordDefinition.getDefinitions()) {
                    if (!definition.isCalculated()) {
                        stringBuilder.append(definition.getName()).append(" ").append(databaseTypeMappings.get(definition.getDefinitionType())).append(",\n");
                    }
                }

                stringBuilder.append("PRIMARY KEY (uuid))");

                queries.add(new SelectQuery(stringBuilder.toString()));
            }
        }

        return queries;
    }

    public HashMap<String, DatabaseColumnModel> getDefinedModelHashMap() {
        return definedModelHashMap;
    }
}
