import {ConfigurationState, definitionAdaptor} from "../reducers/configuration.reducers";
import {selectConfiguration} from "./configuration.selectors";
import {createSelector} from "@ngrx/store";

export const {
  selectAll,
  selectEntities,
  selectIds,
  selectTotal
} = definitionAdaptor.getSelectors();

// create a selector to select entities
export const selectAllYourEntities = createSelector(
  selectConfiguration,
  (state: ConfigurationState) => state.definitions.entities
);

// create a selector to select multiple entities by ids
export const selectYourEntityByIds = (ids: string[] | null) =>
  createSelector(
    selectAllYourEntities,
    (entities) => {
      if (ids == null) {
        return null;
      } else {
        return ids.map(id => entities[id]).filter(entity => entity != null);
      }
    }
  );
