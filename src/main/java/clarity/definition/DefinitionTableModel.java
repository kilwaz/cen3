package clarity.definition;

import data.SelectQuery;
import data.SelectResult;
import data.model.DatabaseAction;
import log.AppLogger;
import org.apache.logging.log4j.Logger;
import utils.ApplicationParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DefinitionTableModel {
    private static Logger log = AppLogger.logger();

    private HashMap<String, DefinitionModel> databaseColumnModelHashMap = new HashMap<>();
    private HashMap<String, DefinitionModel> definedModelHashMap = new HashMap<>();
    private HashMap<String, DefinitionTableModel> childTableModel = new HashMap<>();

    private Boolean databaseTableExists;
    private Boolean definedTableExists;

    private static HashMap<Integer, String> databaseTypeMappings = new HashMap<>();

    static {
        if (ApplicationParams.getRemoteDatabaseDialect().equals(ApplicationParams.ORACLE_DIALECT)) {
            databaseTypeMappings.put(Definition.DEFINITION_TYPE_UNDEFINED, "varchar2(200)");
            databaseTypeMappings.put(Definition.DEFINITION_TYPE_NUMBER, "int");
            databaseTypeMappings.put(Definition.DEFINITION_TYPE_STRING, "varchar2(200)");
            databaseTypeMappings.put(Definition.DEFINITION_TYPE_DURATION, "varchar2(200)");
        } else { // MySQL
            databaseTypeMappings.put(Definition.DEFINITION_TYPE_UNDEFINED, "varchar(200)");
            databaseTypeMappings.put(Definition.DEFINITION_TYPE_NUMBER, "int");
            databaseTypeMappings.put(Definition.DEFINITION_TYPE_STRING, "varchar(200)");
            databaseTypeMappings.put(Definition.DEFINITION_TYPE_DURATION, "varchar(200)");
        }
    }

    private RecordDefinition recordDefinition;

    public DefinitionTableModel(RecordDefinition recordDefinition) {
        this.recordDefinition = recordDefinition;
    }

    // Updates definition map with any new definitions or if an existing definition is renamed
    public void update() {
        definedTableExists = recordDefinition == null;
        var definitionHashMap = recordDefinition.getDefinitionHashMap();
        var childRecordDefinitionHashMap = recordDefinition.getChildRecordDefinitions();

        definedModelHashMap.clear();
        childTableModel.clear();

        for (Definition definition : definitionHashMap.values()) {
            definedModelHashMap.put(definition.getName().toUpperCase(), new DefinitionModel(definition));
        }

        for (RecordDefinitionChild recordDefinitionChild : childRecordDefinitionHashMap.values()) {
            childTableModel.put(recordDefinitionChild.getRecordDefinitionChild().getName(), recordDefinitionChild.getRecordDefinitionChild().getDefinitionTableMode());
        }
    }

    public static HashMap<Integer, String> getDatabaseTypeMappings() {
        return databaseTypeMappings;
    }

    public void verifyTable() {
        fillHashMap(verifyByTable(recordDefinition.getTableNameByState(DatabaseAction.STATE_RAW).toUpperCase()), DatabaseAction.STATE_RAW);
        fillHashMap(verifyByTable(recordDefinition.getTableNameByState(DatabaseAction.STATE_CALC).toUpperCase()), DatabaseAction.STATE_CALC);
        fillHashMap(verifyByTable(recordDefinition.getTableNameByState(DatabaseAction.STATE_STATIC).toUpperCase()), DatabaseAction.STATE_STATIC);
    }

    private void fillHashMap(HashMap<String, DatabaseColumnModel> columnModelHashMap, int state) {
        for (String key : columnModelHashMap.keySet()) {
            if (databaseColumnModelHashMap.containsKey(key)) {
                databaseColumnModelHashMap.get(key).setModelByState(columnModelHashMap.get(key), state);
            } else {
                DefinitionModel definitionModel = new DefinitionModel();
                definitionModel.setModelByState(columnModelHashMap.get(key), state);
                databaseColumnModelHashMap.put(key, definitionModel);
            }
        }
    }

    private HashMap<String, DatabaseColumnModel> verifyByTable(String tableName) {
        HashMap<String, DatabaseColumnModel> columnModelHashMap = new HashMap<>();
        if (recordDefinition != null) {
            if (ApplicationParams.getRemoteDatabaseDialect().equals(ApplicationParams.ORACLE_DIALECT)) {
                var selectResult = (SelectResult) new SelectQuery("SELECT COLUMN_NAME, DATA_TYPE, DATA_LENGTH FROM USER_TAB_COLUMNS WHERE TABLE_NAME = '" + tableName + "'").execute();
                if (selectResult.hasException() || selectResult.hasNoResults()) { // Most likely the table doesn't exist
                    databaseTableExists = false;
                } else {
                    databaseTableExists = true; // If no error then this is true
                    for (var resultRow : selectResult.getResults()) {
                        String columnName = "";
                        String columnType = "";

                        if (resultRow.hasColumn("COLUMN_NAME")) {
                            columnName = resultRow.getString("COLUMN_NAME");
                        }

                        if (resultRow.hasColumn("DATA_TYPE") && resultRow.hasColumn("DATA_LENGTH")) {
                            if (resultRow.getString("DATA_TYPE").equals("VARCHAR2") || resultRow.getString("DATA_TYPE").equals("CHAR")) {
                                columnType = resultRow.getString("DATA_TYPE") + "(" + resultRow.getBigDecimal("DATA_LENGTH") + ")";
                            } else {
                                columnType = resultRow.getString("DATA_TYPE");
                            }
                        }

                        columnModelHashMap.put(columnName, new DatabaseColumnModel(columnName, columnType));
                    }
                }
            } else if (ApplicationParams.getRemoteDatabaseDialect().equals(ApplicationParams.MYSQL_DIALECT)) {
//                var selectResult = (SelectResult) new SelectQuery("describe " + tableName).execute();
//                if (selectResult.hasException()) { // Most likely the table doesn't exist
//                    databaseTableExists = false;
//                } else {
//                    databaseTableExists = true; // If no error then this is true
//                    for (var resultRow : selectResult.getResults()) {
//                        String columnName = "";
//                        String columnType = "";
//
//                        //TODO: Why is this different?  Different MySQL/MariaDB versions?
//                        if (resultRow.hasColumn("Field")) {
//                            columnName = resultRow.getString("Field");
//                        } else if (resultRow.hasColumn("COLUMN_NAME")) {
//                            columnName = resultRow.getString("COLUMN_NAME");
//                        }
//
//                        if (resultRow.hasColumn("Type")) {
//                            columnType = resultRow.getString("Type");
//                        } else if (resultRow.hasColumn("COLUMN_TYPE")) {
//                            columnType = resultRow.getString("COLUMN_TYPE");
//                        }
//
//                        databaseColumnModelHashMap.put(columnName, new DatabaseColumnModel(columnName, columnType));
//                    }
//                }
            }
        }

        return columnModelHashMap;
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
        processDeltaQuery(queries, recordDefinition.getTableNameByState(DatabaseAction.STATE_RAW), DatabaseAction.STATE_RAW);
        processDeltaQuery(queries, recordDefinition.getTableNameByState(DatabaseAction.STATE_CALC), DatabaseAction.STATE_CALC);
        processDeltaQuery(queries, recordDefinition.getTableNameByState(DatabaseAction.STATE_STATIC), DatabaseAction.STATE_STATIC);

        return queries;
    }

    private List<SelectQuery> processDeltaQuery(List<SelectQuery> queries, String tableName, int state) {
        if (recordDefinition != null) {
            if (databaseTableExists) {
                for (String definedModelKey : definedModelHashMap.keySet()) {
                    DatabaseColumnModel databaseColumn = databaseColumnModelHashMap.get(definedModelKey).getModelByState(state);
                    ;
                    DatabaseColumnModel definedColumn = definedModelHashMap.get(definedModelKey).getModelByState(state);
                    ;

                    if (databaseColumn == null && definedColumn != null) {
                        queries.add(new SelectQuery("alter table " + tableName + " add " + definedModelKey + " " + definedColumn.getColumnType()));
                    } else if (databaseColumn != null && definedColumn == null) {
                        queries.add(new SelectQuery("alter table " + tableName + " drop " + definedModelKey));
                    }

                    if (databaseColumn != null && definedColumn != null && !databaseColumn.getColumnType().equalsIgnoreCase(definedColumn.getColumnType())) {
                        queries.add(new SelectQuery("alter table " + tableName + " modify column " + definedColumn.getColumnName() + " " + definedColumn.getColumnType()));
                    }
                }
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("create table ").append(tableName).append(" (\n")
                        .append("uuid char(36) NOT NULL,\n");

                for (Definition definition : recordDefinition.getDefinitions()) {
                    if (state == DatabaseAction.STATE_RAW) {
                        if (!definition.isCalculated()) {
                            stringBuilder.append(definition.getName()).append(" ").append(databaseTypeMappings.get(definition.getDefinitionType())).append(",\n");
                        }
                    } else if (state == DatabaseAction.STATE_CALC) {
                        if (definition.isCalculated()) {
                            stringBuilder.append(definition.getName()).append(" ").append(databaseTypeMappings.get(definition.getDefinitionType())).append(",\n");
                        }
                    } else if (state == DatabaseAction.STATE_STATIC) {
                        stringBuilder.append(definition.getName()).append(" ").append(databaseTypeMappings.get(definition.getDefinitionType())).append(",\n");
                    }
                }

                stringBuilder.append("PRIMARY KEY (uuid))");

                queries.add(new SelectQuery(stringBuilder.toString()));
            }
        }

        return queries;
    }

    public HashMap<String, DefinitionModel> getDefinedModelHashMap() {
        return definedModelHashMap;
    }
}
