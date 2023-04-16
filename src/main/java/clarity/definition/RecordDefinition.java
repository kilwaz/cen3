package clarity.definition;

import data.model.DatabaseAction;
import data.model.DatabaseObject;
import data.model.dao.DefinitionGroupDAO;
import error.Error;
import log.AppLogger;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class RecordDefinition extends DatabaseObject {
    private static Logger log = AppLogger.logger();

    private String name = "";
    private HashMap<String, Definition> definitionHashMap = null;
    private DefinitionTableModel definitionTableMode;
    private HashMap<String, RecordDefinitionChild> childRecordDefinitions = new HashMap<>();
    private Definition primaryKey = null;

    public RecordDefinition() {
        name = hashCode() + "";
        definitionTableMode = new DefinitionTableModel(this);
    }

    public static RecordDefinition define(String name) {
        RecordDefinition recordDefinition;
        Definitions definitions = Definitions.getInstance();
        if (definitions.findRecordDefinition(name) == null) {
            recordDefinition = RecordDefinition.create(RecordDefinition.class);
            recordDefinition.name(name);
            definitions.addRecordDefinition(recordDefinition);
        } else { // If definition already exists, get the already defined version
            recordDefinition = definitions.findRecordDefinition(name);
        }

        return recordDefinition;
    }

    public RecordDefinition addDefinitions(String... references) {
        for (String reference : references) {
            addDefinition(reference);
        }

        return this;
    }

    public RecordDefinition addDefinition(String reference) {
        Definition definition = Definitions.getInstance().findDefinition(reference);

        if (definition != null) {
            if (getDefinitionHashMap().get(reference) == null) { // Don't add a definition again if it is already there
                DefinitionGroup definitionGroup = DefinitionGroup.create(DefinitionGroup.class);
                definitionGroup.definition(definition);
                definitionGroup.recordDefinition(this);
                definitionGroup.save();

                addDefinition(definition);
            }
        } else {
            Error.CLARITY_REFERENCE_NOT_FOUND.record()
                    .additionalInformation("Definition " + reference + " not found to add to record " + this.getName())
                    .create();
        }

        return this;
    }

    public RecordDefinition addChildRecordDefinition(RecordDefinitionChild recordDefinitionChild) {
        childRecordDefinitions.put(recordDefinitionChild.getRecordDefinitionChild().getName(), recordDefinitionChild);

        return this;
    }

    public RecordDefinition defineChildRecordDefinition(RecordDefinition recordDefinition) {
        if (recordDefinition != null) {
            RecordDefinitionChild recordDefinitionChild = RecordDefinitionChild.define(this, recordDefinition);
            childRecordDefinitions.put(recordDefinition.getName(), recordDefinitionChild);
            definitionTableMode.update();
        } else {
            Error.CLARITY_NULL_DEFINITION.record().create();
        }

        return this;
    }

    public RecordDefinition addDefinition(Definition definition) {
        if (definition != null) {
            getDefinitionHashMap().put(definition.getName(), definition);
            definitionTableMode.update();
        } else {
            Error.CLARITY_NULL_DEFINITION.record().create();
        }

        return this;
    }

    public Definition getDefinition(String reference) {
        return getDefinitionHashMap().get(reference);
    }

    public HashMap<String, Definition> getDefinitionHashMap() {
        if (this.definitionHashMap == null) {
            this.definitionHashMap = new HashMap<>();
            DefinitionGroupDAO definitionGroupDAO = new DefinitionGroupDAO();
            List<DefinitionGroup> definitionGroups = definitionGroupDAO.getDefinitionGroupByRecordDefinition(this);

            for (DefinitionGroup definitionGroup : definitionGroups) {
                this.definitionHashMap.put(definitionGroup.getDefinition().getName(), definitionGroup.getDefinition());
            }
        }

        return this.definitionHashMap;
    }

    public List<Definition> getDefinitions() {
        return new ArrayList<>(getDefinitionHashMap().values());
    }

    public RecordDefinition name(String name) {
        String oldName = this.name;
        this.name = name;
        Definitions.getInstance().updateRecordDefinitionName(this, oldName);
        this.save();
        definitionTableMode.update();
        return this;
    }

    public String getName() {
        return name;
    }

    public Boolean hasDefinition(Definition definition) {
        return definitionHashMap.containsKey(definition.getName());
    }

    public DefinitionTableModel getDefinitionTableMode() {
        return definitionTableMode;
    }

    public String getBaseTableName() {
        return "rec_" + getName().toLowerCase();
    }

    public String getTableNameByState(int state) {
        if (state == RecordState.RAW) {
            return "rec_" + getName().toLowerCase() + "_raw";
        } else if (state == RecordState.CALC) {
            return "rec_" + getName().toLowerCase() + "_calc";
        } else if (state == RecordState.STATIC) {
            return "rec_" + getName().toLowerCase() + "_static";
        }

        return null;
    }

    public HashMap<String, RecordDefinitionChild> getChildRecordDefinitions() {
        return childRecordDefinitions;
    }

    public Definition getPrimaryKey() {
        return primaryKey;
    }

    public UUID getPrimaryKeyUUID() {
        if (primaryKey != null) {
            return primaryKey.getUuid();
        } else {
            return null;
        }
    }

    public void primaryKey(Definition primaryKey) {
        this.primaryKey = primaryKey;
        this.save();
    }
}
