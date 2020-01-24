package clarity.definition;

import error.Error;
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

    public RecordDefinition addDefinitions(String... references) {
        for (String reference : references) {
            addDefinition(reference);
        }

        return this;
    }

    public RecordDefinition addDefinition(String reference) {
        addDefinition(Definitions.getInstance().findDefinition(reference));
        return this;
    }

    public RecordDefinition addDefinition(Definition definition) {
        if (definition != null) {
            definitionHashMap.put(definition.getName(), definition);
        } else {
            Error.CLARITY_NULL_DEFINITION.record().create();
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
