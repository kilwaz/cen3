// NGRX
import {createFeatureSelector, createSelector} from '@ngrx/store';
import {ConfigurationState, DefinitionsState} from "../reducers/configuration.reducers";

// Selectors
export const selectConfiguration = createFeatureSelector<ConfigurationState>('configuration');

export const recordDefinitions = createSelector(selectConfiguration, configuration => configuration?.recordDefinitions);
export const formulaContexts = createSelector(selectConfiguration, configuration => configuration?.formulaContexts);
export const definitions = createSelector(selectConfiguration, configuration => configuration?.definitions);
export const worksheetConfigs = createSelector(selectConfiguration, configuration => configuration?.worksheetConfigs);

export const selectedRecordDefinition = createSelector(selectConfiguration, configuration => configuration?.selectedRecordDefinition);
export const selectedDefinition = createSelector(selectConfiguration, configuration => configuration?.selectedDefinition);
export const selectedFormulaContext = createSelector(selectConfiguration, configuration => configuration?.selectedFormulaContext);
export const selectedType = createSelector(selectConfiguration, configuration => configuration?.selectedType);
export const selectedWorksheetConfig = createSelector(selectConfiguration, configuration => configuration?.selectedWorksheetConfig);


// create a selector to select entities
export const selectAllYourEntities = createSelector(
  definitions,
  (state: DefinitionsState) => state.entities
);

// create a selector to select a single entity by id
export const selectYourEntityById = (id: string) =>
  createSelector(
    selectAllYourEntities,
    (entities) => entities[id]
  );
