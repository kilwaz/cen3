// NGRX
import {createFeatureSelector, createSelector} from '@ngrx/store';
import {HierarchyState} from "../reducers/hierarchy.reducers";

// Selectors
export const selectHierarchy = createFeatureSelector<HierarchyState>('hierarchy');

export const hierarchyItems = createSelector(selectHierarchy, hierarchy => hierarchy?.hierarchyItems);

