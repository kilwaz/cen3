// NGRX
import {createFeatureSelector, createSelector} from '@ngrx/store';
import {HierarchyState} from "../reducers/hierarchy.reducers";
import {HierarchyListItem} from "../../../wsObjects/hierarchyListItem";

// Selectors
export const selectHierarchy = createFeatureSelector<HierarchyState>('hierarchy');

export const hierarchyItems = createSelector(selectHierarchy, hierarchy => hierarchy?.hierarchyItems);

export const selectedItem = createSelector(selectHierarchy, hierarchy => hierarchy?.selectedItem);

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
