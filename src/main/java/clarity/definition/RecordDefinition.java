package clarity.definition;

import log.AppLogger;
import org.apache.log4j.Logger;

import java.util.HashMap;

public class RecordDefinition {
    private static Logger log = AppLogger.logger();

    private String name = "";
    private HashMap<String, Definition> definitionHashMap = new HashMap<>();

    public RecordDefinition() {
        name = hashCode() + "";
    }

    public static RecordDefinition define() {
        RecordDefinition recordDefinition = new RecordDefinition();
        Definitions.getInstance().addRecordDefinition(recordDefinition);
        return recordDefinition;
    }

    public RecordDefinition addDefinition(Definition definition) {
        if (definition != null) {
            definitionHashMap.put(definition.getName(), definition);
        } else {
            //TODO: Make this an actual error that can be thrown
            log.info("Attempted to load null definition");
        }

        return this;
    }

    public RecordDefinition name(String name) {
        String oldName = this.name;
        this.name = name;
        Definitions.getInstance().updateRecordDefinitionName(this, oldName);
        return this;
    }

    public String getName() {
        return name;
    }
}
