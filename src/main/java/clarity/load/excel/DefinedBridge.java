package clarity.load.excel;

import clarity.definition.Definition;
import clarity.definition.RecordDefinition;
import data.model.DatabaseObject;
import log.AppLogger;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

public class DefinedBridge extends DatabaseObject {
    private static Logger log = AppLogger.logger();

    private String columnTitle;
    private Definition definition;
    private RecordDefinition recordDefinition;
    private DefinedTemplate definedTemplate;

    public DefinedBridge() {
        super();
    }

    public DefinedBridge(UUID uuid, String columnName, Definition definition, RecordDefinition recordDefinition, DefinedTemplate definedTemplate) {
        super(uuid);
        this.columnTitle = columnName;
        this.definition = definition;
        this.recordDefinition = recordDefinition;
        this.definedTemplate = definedTemplate;
    }

    public String getColumnTitle() {
        return columnTitle;
    }

    public void columnTitle(String columnTitle) {
        this.columnTitle = columnTitle;
    }

    public Definition getDefinition() {
        return definition;
    }

    public String getDefinitionUUID() {
        if (definition == null) {
            return null;
        }
        return definition.getUuidString();
    }

    public void definition(Definition definition) {
        this.definition = definition;
    }

    public RecordDefinition getRecordDefinition() {
        return recordDefinition;
    }

    public String getRecordDefinitionUUID() {
        return recordDefinition.getUuidString();
    }

    public void recordDefinition(RecordDefinition recordDefinition) {
        this.recordDefinition = recordDefinition;
    }

    public DefinedTemplate getDefinedTemplate() {
        return definedTemplate;
    }

    public String getDefinedTemplateUUID() {
        return definedTemplate.getUuidString();
    }

    public void definedTemplate(DefinedTemplate definedTemplate) {
        this.definedTemplate = definedTemplate;
    }
}