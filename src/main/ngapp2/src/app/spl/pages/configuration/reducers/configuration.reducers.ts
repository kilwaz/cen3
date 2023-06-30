// Actions
import {ConfigurationActions, ConfigurationActionTypes} from '../actions/configuration.actions';
import {createEntityAdapter, EntityAdapter, EntityState} from "@ngrx/entity";
import {RecordDefinitionDataItem} from "../../../wsObjects/recordDefinitionDataItem";
import {DefinitionDataItem} from "../../../wsObjects/definitionDataItem";

export interface ConfigurationState {
  recordDefinitions: RecordDefinitionsState,
  definitions: DefinitionsState,
  selectedRecordDefinition: RecordDefinitionDataItem,
  selectedDefinition: DefinitionDataItem
}

// RecordDefinitionsState
export interface RecordDefinitionsState extends EntityState<RecordDefinitionDataItem> {
}

export const recordDefinitionAdaptor: EntityAdapter<RecordDefinitionDataItem> = createEntityAdapter<RecordDefinitionDataItem>({
  selectId: hierarchyItem => hierarchyItem.uuid
});
export const initialRecordDefinitionsState: RecordDefinitionsState = recordDefinitionAdaptor.getInitialState({});


// DefinitionsState
export interface DefinitionsState extends EntityState<DefinitionDataItem> {
}

export const definitionAdaptor: EntityAdapter<DefinitionDataItem> = createEntityAdapter<DefinitionDataItem>({
  selectId: hierarchyItem => hierarchyItem.uuid
});
export const initialDefinitionsState: DefinitionsState = definitionAdaptor.getInitialState({});


export const initialConfigurationState: ConfigurationState = {
  recordDefinitions: initialRecordDefinitionsState,
  definitions: initialDefinitionsState,
  selectedRecordDefinition: null,
  selectedDefinition: null
};

export function configurationReducer(state = initialConfigurationState, action: ConfigurationActions): ConfigurationState {
  switch (action.type) {
    case ConfigurationActionTypes.LoadRecordDefinitions: {
      return {
        ...state,
        recordDefinitions: recordDefinitionAdaptor.setAll(action.payload.recordDefinitions, state.recordDefinitions),
      };
    }
    case ConfigurationActionTypes.LoadDefinitions: {
      return {
        ...state,
        definitions: definitionAdaptor.setAll(action.payload.definitions, state.definitions),
      };
    }
    case ConfigurationActionTypes.SelectDefinition: {
      return {
        ...state,
        selectedDefinition: action.payload.definition,
      };
    }
    case ConfigurationActionTypes.RequestDefinitions: {
      return {
        ...state,
        selectedRecordDefinition: action.payload.recordDefinition,
      };
    }
    case ConfigurationActionTypes.UpdateDefinition: {
      return {
        ...state,
        definitions: definitionAdaptor.updateOne(action.payload.update, state.definitions)
      };
    }
    default:
      return state;
  }
}
