package clarity.definition;

import data.model.DatabaseObject;
import data.model.dao.DefinitionGroupDAO;
import error.Error;
import log.AppLogger;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecordDefinition extends DatabaseObject {
    private static Logger log = AppLogger.logger();

    private String name = "";
    private HashMap<String, Definition> definitionHashMap = null;
    private DefinitionTableModel definitionTableMode;

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

    public DefinitionTableModel getDefinitionTableMode() {
        return definitionTableMode;
    }
}
