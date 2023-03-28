// NGRX
import {createFeatureSelector, createSelector} from '@ngrx/store';
import {SideBarState} from "../reducers/sidebar.reducers";

// Selectors
export const selectRoles = createFeatureSelector<SideBarState>('sidebar');

// export const loadRole = createSelector(selectRoles, textCases => textCases?.textToProcess);

