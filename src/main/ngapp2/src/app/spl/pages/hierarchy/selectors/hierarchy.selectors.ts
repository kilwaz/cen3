// NGRX
import {createFeatureSelector, createSelector} from '@ngrx/store';
import {adaptor, HierarchyState} from "../reducers/hierarchy.reducers";
import {HierarchyListItem} from "../../../wsObjects/hierarchyListItem";

// Selectors
export const selectHierarchy = createFeatureSelector<HierarchyState>('hierarchy');

export const hierarchyItems = createSelector(selectHierarchy, hierarchy => hierarchy?.hierarchyItems);

export const selectedItem = createSelector(selectHierarchy, hierarchy => hierarchy?.selectedItem);

export const reloadHierarchy = createSelector(selectHierarchy, hierarchy => hierarchy?.reloadHierarchy);

export const {
  selectAll,
  selectEntities,
  selectIds,
  selectTotal
} = adaptor.getSelectors();

// create a selector to select entities
export const selectAllYourEntities = createSelector(
  selectHierarchy,
  (state: HierarchyState) => state.hierarchyItems.entities
);

// create a selector to select a single entity by id
export const selectYourEntityById = (id: string) =>
  createSelector(
    selectAllYourEntities,
    (entities) => entities[id]
  );

// create a selector to select multiple entities by ids
export const selectYourEntityByIds = (ids: string[]) =>
  createSelector(
    selectAllYourEntities,
    entities => ids.map(id => entities[id]).filter(entity => entity != null)
  );

export const isSelectedItem = (hierarchyListItem: HierarchyListItem) =>
  createSelector(
    selectedItem,
    (selectedItem) => {
      if (selectedItem === null || hierarchyListItem === null) {
        return false;
      } else {
        return selectedItem.title === hierarchyListItem.title;
      }
    }
  );
