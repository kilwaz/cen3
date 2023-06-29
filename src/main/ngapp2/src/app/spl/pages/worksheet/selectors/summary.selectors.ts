// NGRX
import {createFeatureSelector, createSelector} from '@ngrx/store';
import {SummaryState} from "../reducers/summary.reducers";
import {selectWorksheet} from "./worksheet.selectors";

// Selectors
export const selectSummary = createFeatureSelector<SummaryState>('summary');

export const content = createSelector(selectSummary, summary => summary?.content);
