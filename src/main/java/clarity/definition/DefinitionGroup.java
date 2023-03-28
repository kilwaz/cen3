package clarity.definition;

import data.model.DatabaseObject;
import log.AppLogger;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

public class DefinitionGroup extends DatabaseObject {
    private static Logger log = AppLogger.logger();

    private Definition definition;
    private RecordDefinition recordDefinition;

    public DefinitionGroup() {
        super();
    }

    public DefinitionGroup(UUID uuid, Definition definition, RecordDefinition recordDefinition) {
        super(uuid);
        this.definition = definition;
        this.recordDefinition = recordDefinition;
    }

    public Definition getDefinition() {
        return definition;
    }

    public String getDefinitionUUID() {
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
}
