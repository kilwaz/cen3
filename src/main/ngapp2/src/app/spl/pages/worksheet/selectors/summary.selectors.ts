// NGRX
import {createFeatureSelector, createSelector} from '@ngrx/store';
import {SummaryState} from "../reducers/summary.reducers";

// Selectors
export const selectSummary = createFeatureSelector<SummaryState>('summary');

export const content = createSelector(selectSummary, summary => summary?.content);
export const configurableUi = createSelector(selectSummary, summary => summary?.configurableUi);
