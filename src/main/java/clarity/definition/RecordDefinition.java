package clarity.definition;

import log.AppLogger;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public Definition getDefinition(String reference) {
        return definitionHashMap.get(reference);
    }

    public List<Definition> getDefinitions() {
        return new ArrayList<>(definitionHashMap.values());
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
