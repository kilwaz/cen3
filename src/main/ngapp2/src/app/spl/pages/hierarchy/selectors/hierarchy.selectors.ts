// NGRX
import {createFeatureSelector, createSelector} from '@ngrx/store';
import {HierarchyState} from "../reducers/hierarchy.reducers";

// Selectors
export const selectTextCases = createFeatureSelector<HierarchyState>('hierarchy');

export const textToProcess = createSelector(selectTextCases, hierarchy => hierarchy?.textToProcess);

