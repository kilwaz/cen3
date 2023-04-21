// NGRX
import {createFeatureSelector, createSelector} from '@ngrx/store';
import {WorksheetState} from '../reducers/worksheet.reducers';

// Selectors
export const selectWorksheet = createFeatureSelector<WorksheetState>('worksheet');

export const requestID = createSelector(selectWorksheet, worksheet => worksheet?.requestID);
export const worksheetRecords = createSelector(selectWorksheet, worksheet => worksheet?.worksheetRecords);

