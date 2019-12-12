package clarity;

import log.AppLogger;
import org.apache.log4j.Logger;

import java.util.HashMap;

public class Definitions {
    private static Logger log = AppLogger.logger();
    private static Definitions instance;

    private HashMap<String, Definition> definitionHashMap = new HashMap<>();

    public Definition findDefinition(String reference) {
        return definitionHashMap.get(reference);
    }

    public void updateDefinitionName(Definition definition, String oldName) {
        definitionHashMap.put(definition.getName(), definition);
        definitionHashMap.remove(oldName);
    }

    public void recordDefinition(Definition definition) {
        definitionHashMap.put(definition.getName(), definition);
    }

    public static Definitions getInstance() {
        if (instance == null) {
            instance = new Definitions();
        }
        return instance;
    }
}
