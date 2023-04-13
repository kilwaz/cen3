package clarity.definition;

import data.model.DatabaseAction;

public class DefinitionModel {

    private DatabaseColumnModel rawModel;
    private DatabaseColumnModel calcModel;
    private DatabaseColumnModel staticModel;

    public DefinitionModel() {

    }

    public DefinitionModel(Definition definition) {
        // All non calculated fields should be included in the raw model
        if (!definition.isCalculated()) {
            this.rawModel = new DatabaseColumnModel(definition.getName(), DefinitionTableModel.getDatabaseTypeMappings().get(definition.getDefinitionType()));
        }
        // All calculated fields should be part of the calculated model
        if (definition.isCalculated()) {
            this.calcModel = new DatabaseColumnModel(definition.getName(), DefinitionTableModel.getDatabaseTypeMappings().get(definition.getDefinitionType()));
        }
        // Everything goes into the static model
        this.staticModel = new DatabaseColumnModel(definition.getName(), DefinitionTableModel.getDatabaseTypeMappings().get(definition.getDefinitionType()));
    }

    public DatabaseColumnModel getModelByState(int state) {
        if (state == DatabaseAction.STATE_RAW) {
            return rawModel;
        } else if (state == DatabaseAction.STATE_CALC) {
            return calcModel;
        } else if (state == DatabaseAction.STATE_STATIC) {
            return staticModel;
        }

        return null;
    }

    public DatabaseColumnModel setModelByState(DatabaseColumnModel model, int state) {
        if (state == DatabaseAction.STATE_RAW) {
            return rawModel = model;
        } else if (state == DatabaseAction.STATE_CALC) {
            return calcModel = model;
        } else if (state == DatabaseAction.STATE_STATIC) {
            return staticModel = model;
        }

        return null;
    }

    public Boolean hasModelByState(int state) {
        if (state == DatabaseAction.STATE_RAW) {
            return rawModel != null;
        } else if (state == DatabaseAction.STATE_CALC) {
            return calcModel != null;
        } else if (state == DatabaseAction.STATE_STATIC) {
            return staticModel != null;
        }

        return null;
    }
}