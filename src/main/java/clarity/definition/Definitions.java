package clarity.definition;

import data.SelectQuery;
import data.model.dao.DefinitionDAO;
import data.model.dao.RecordDefinitionChildDAO;
import data.model.dao.RecordDefinitionDAO;
import log.AppLogger;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Definitions {
    private static Logger log = AppLogger.logger();
    private static Definitions instance;

    private HashMap<String, Definition> definitionHashMap = new HashMap<>();
    private HashMap<String, RecordDefinition> recordDefinitionHashMap = new HashMap<>();
    private List<RecordDefinitionChild> recordDefinitionChildList = new ArrayList<>();

    public Definitions() {

    }

    private void clear() {
        this.definitionHashMap = new HashMap<>();
        this.recordDefinitionHashMap = new HashMap<>();
    }

    public void rebuild() {
        clear();
        build();
    }

    private void build() {
        // Load in definitions
        DefinitionDAO definitionDAO = new DefinitionDAO();
        List<Definition> definitions = definitionDAO.getAllDefinitions();

        for (Definition definition : definitions) { // Add them all as referenceable first
            this.addDefinition(definition);
        }
        for (Definition definition : definitions) { // Build each one once all references are available, so needs different loop
            definition.build();
        }

        // Load in the record definitions from the database and process them
        RecordDefinitionDAO recordDefinitionDAO = new RecordDefinitionDAO();
        List<RecordDefinition> recordDefinitions = recordDefinitionDAO.getAllRecordDefinitions();

        for (RecordDefinition recordDefinition : recordDefinitions) {
            this.addRecordDefinition(recordDefinition);
            recordDefinition.getDefinitionTableMode().verifyTable();
            List<SelectQuery> selectQueries = recordDefinition.getDefinitionTableMode().getDeltaQueries();
            for (SelectQuery selectQuery : selectQueries) {
                selectQuery.execute();
            }
        }

        // Load in record definition children mappings
        RecordDefinitionChildDAO recordDefinitionChildDAO = new RecordDefinitionChildDAO();
        List<RecordDefinitionChild> recordDefinitionChildren = recordDefinitionChildDAO.getAllRecordDefinitionChildren();

        for (RecordDefinitionChild recordDefinitionChild : recordDefinitionChildren) {
            this.addRecordDefinitionChild(recordDefinitionChild);
            RecordDefinition recordDefinition = findRecordDefinition(recordDefinitionChild.getRecordDefinitionParent().getName());

            if (recordDefinition != null) {
                recordDefinition.addChildRecordDefinition(recordDefinitionChild);
            } else {
                log.info("Searching for record '" + recordDefinitionChild.getRecordDefinitionParent().getName() + "' but could not be found");
            }
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

    public void addRecordDefinitionChild(RecordDefinitionChild recordDefinitionChild) {
        recordDefinitionChildList.add(recordDefinitionChild);
    }

    public static Definitions getInstance() {
        if (instance == null) {
            instance = new Definitions();
            instance.build();
        }
        return instance;
    }

    public HashMap<String, Definition> getDefinitionHashMap() {
        return definitionHashMap;
    }

    public HashMap<String, RecordDefinition> getRecordDefinitionHashMap() {
        return recordDefinitionHashMap;
    }

    public RecordDefinition getRecordDefinition(String name) {
        return recordDefinitionHashMap.get(name.toLowerCase());
    }

    public Definition getDefinition(String name) {
        return definitionHashMap.get(name.toLowerCase());
    }
}
