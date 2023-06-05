// NGRX
import {createFeatureSelector, createSelector} from '@ngrx/store';
import {DashboardState} from "../reducers/dashboard.reducers";

// Selectors
export const selectTextCases = createFeatureSelector<DashboardState>('dashboard');

export const textToProcess = createSelector(selectTextCases, dashboard => dashboard?.textToProcess);

