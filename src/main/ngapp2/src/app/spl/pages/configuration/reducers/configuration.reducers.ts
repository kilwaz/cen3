// Actions
import {AddNewWorksheetConfig, ConfigurationActions, ConfigurationActionTypes} from '../actions/configuration.actions';
import {createEntityAdapter, EntityAdapter, EntityState} from "@ngrx/entity";
import {RecordDefinitionDataItem} from "../../../wsObjects/recordDefinitionDataItem";
import {DefinitionDataItem} from "../../../wsObjects/definitionDataItem";
import {FormulaContextDataItem} from "../../../wsObjects/formulaContextDataItem";
import {WorksheetConfigDataItem} from "../../../wsObjects/worksheetConfigDataItem";
import {WebWorksheetConfigDataItem} from "../../../wsObjects/webWorksheetConfigDataItem";

export interface ConfigurationState {
  recordDefinitions: RecordDefinitionsState,
  formulaContexts: FormulaContextState,
  definitions: DefinitionsState,
  worksheetConfigs: WorksheetConfigsState,
  worksheetConfigItems: WorksheetConfigItemsState,
  selectedRecordDefinition: RecordDefinitionDataItem,
  selectedDefinition: DefinitionDataItem,
  selectedFormulaContext: FormulaContextDataItem,
  selectedWorksheetConfig: WorksheetConfigDataItem,
  selectedType: string
}

// RecordDefinitionsState
export interface RecordDefinitionsState extends EntityState<RecordDefinitionDataItem> {
}

export const recordDefinitionAdaptor: EntityAdapter<RecordDefinitionDataItem> = createEntityAdapter<RecordDefinitionDataItem>({
  selectId: recordDefinition => recordDefinition.uuid
});
export const initialRecordDefinitionsState: RecordDefinitionsState = recordDefinitionAdaptor.getInitialState({});


// FormulaContextState
export interface FormulaContextState extends EntityState<FormulaContextDataItem> {
}

export const formulaContextAdaptor: EntityAdapter<FormulaContextDataItem> = createEntityAdapter<FormulaContextDataItem>({
  selectId: formulaContext => formulaContext.uuid
});
export const initialFormulaContextState: FormulaContextState = formulaContextAdaptor.getInitialState({});

// DefinitionsState
export interface DefinitionsState extends EntityState<DefinitionDataItem> {
}

export const definitionAdaptor: EntityAdapter<DefinitionDataItem> = createEntityAdapter<DefinitionDataItem>({
  selectId: definition => definition.uuid
});
export const initialDefinitionsState: DefinitionsState = definitionAdaptor.getInitialState({});

// WorksheetConfigsState
export interface WorksheetConfigsState extends EntityState<WorksheetConfigDataItem> {
}

export const worksheetConfigAdaptor: EntityAdapter<WorksheetConfigDataItem> = createEntityAdapter<WorksheetConfigDataItem>({
  selectId: worksheetConfig => worksheetConfig.name
});
export const initialWorksheetConfigState: WorksheetConfigsState = worksheetConfigAdaptor.getInitialState({});



// WorksheetConfigItemsState
export interface WorksheetConfigItemsState extends EntityState<WebWorksheetConfigDataItem> {
}

export const worksheetConfigItemsAdaptor: EntityAdapter<WebWorksheetConfigDataItem> = createEntityAdapter<WebWorksheetConfigDataItem>({
  selectId: worksheetConfig => worksheetConfig.uuid
});
export const initialWorksheetConfigItemsState: WorksheetConfigItemsState = worksheetConfigItemsAdaptor.getInitialState({});





export const initialConfigurationState: ConfigurationState = {
  recordDefinitions: initialRecordDefinitionsState,
  formulaContexts: initialFormulaContextState,
  definitions: initialDefinitionsState,
  worksheetConfigs: initialWorksheetConfigState,
  worksheetConfigItems: initialWorksheetConfigItemsState,
  selectedRecordDefinition: null,
  selectedDefinition: null,
  selectedFormulaContext: null,
  selectedWorksheetConfig: null,
  selectedType: null
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
        selectedFormulaContext: action.payload.formulaContext
      };
    }
    case ConfigurationActionTypes.UpdateDefinition: {
      return {
        ...state,
        definitions: definitionAdaptor.updateOne(action.payload.update, state.definitions)
      };
    }
    case ConfigurationActionTypes.SelectType: {
      return {
        ...state,
        selectedType: action.payload.selectedType
      };
    }
    case ConfigurationActionTypes.LoadFormulaContexts: {
      return {
        ...state,
        formulaContexts: formulaContextAdaptor.setAll(action.payload.formulaContexts, state.formulaContexts)
      };
    }
    case ConfigurationActionTypes.LoadWorksheetConfigs: {
      return {
        ...state,
        worksheetConfigs: worksheetConfigAdaptor.setAll(action.payload.worksheetConfigs, state.worksheetConfigs)
      };
    }
    case ConfigurationActionTypes.SelectWorksheetConfig: {
      return {
        ...state,
        selectedWorksheetConfig: action.payload.worksheetConfig,
      };
    }
    case ConfigurationActionTypes.UpdateWorksheetConfig: {
      return {
        ...state,
        definitions: definitionAdaptor.updateOne(action.payload.update, state.definitions)
      };
    }
    default:
      return state;
  }
}
