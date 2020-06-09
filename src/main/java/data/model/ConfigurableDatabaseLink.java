package data.model;

import clarity.definition.RecordDefinition;

import java.lang.reflect.Method;

public class ConfigurableDatabaseLink extends DatabaseLink {

    private RecordDefinition recordDefinition;

    public ConfigurableDatabaseLink(RecordDefinition recordDefinition) {
        this.recordDefinition = recordDefinition;
        setTableName(recordDefinition.getName());
        setLinkClass(clarity.Record.class);
    }

    public void link(String databaseColumn, Method objectSaveMethod, Method objectLoadMethod) {
        super.getModelColumns().add(new ModelColumn(databaseColumn, objectSaveMethod, objectLoadMethod, ModelColumn.STANDARD_COLUMN));
    }

    public RecordDefinition getRecordDefinition() {
        return recordDefinition;
    }
}
