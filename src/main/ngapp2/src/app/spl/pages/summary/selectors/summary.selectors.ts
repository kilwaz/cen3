// NGRX
import {createFeatureSelector, createSelector} from '@ngrx/store';
import {SummaryState} from "../reducers/summary.reducers";

// Selectors
export const selectTextCases = createFeatureSelector<SummaryState>('summary');

export const textToProcess = createSelector(selectTextCases, summary => summary?.textToProcess);

