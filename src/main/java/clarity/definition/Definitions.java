package clarity.definition;

import data.model.dao.DefinitionDAO;
import data.model.dao.RecordDefinitionDAO;
import log.AppLogger;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;

public class Definitions {
    private static Logger log = AppLogger.logger();
    private static Definitions instance;

    private HashMap<String, Definition> definitionHashMap = new HashMap<>();
    private HashMap<String, RecordDefinition> recordDefinitionHashMap = new HashMap<>();

    public Definitions() {
        DefinitionDAO definitionDAO = new DefinitionDAO();

        List<Definition> definitions = definitionDAO.getAllDefinitions();
        for (Definition definition : definitions) {
            this.addDefinition(definition);
        }

        RecordDefinitionDAO recordDefinitionDAO = new RecordDefinitionDAO();

        List<RecordDefinition> recordDefinitions = recordDefinitionDAO.getAllRecordDefinitions();
        for (RecordDefinition recordDefinition : recordDefinitions) {
            this.addRecordDefinition(recordDefinition);
        }
    }

    public Definition findDefinition(String reference) {
        return definitionHashMap.get(reference.toLowerCase());
    }

    public RecordDefinition findRecordDefinition(String reference) {
        return recordDefinitionHashMap.get(reference.toLowerCase());
    }

    public void updateDefinitionName(Definition definition, String oldName) {
        definitionHashMap.put(definition.getName().toLowerCase(), definition);
        definitionHashMap.remove(oldName);
    }

    public void updateRecordDefinitionName(RecordDefinition recordDefinition, String oldName) {
        recordDefinitionHashMap.put(recordDefinition.getName().toLowerCase(), recordDefinition);
        recordDefinitionHashMap.remove(oldName);
    }

    public void addDefinition(Definition definition) {
        definitionHashMap.put(definition.getName().toLowerCase(), definition);
    }

    public void addRecordDefinition(RecordDefinition recordDefinition) {
        recordDefinitionHashMap.put(recordDefinition.getName().toLowerCase(), recordDefinition);
    }

    public static Definitions getInstance() {
        if (instance == null) {
            instance = new Definitions();
        }
        return instance;
    }
}
