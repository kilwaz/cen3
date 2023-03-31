// NGRX
import {createFeatureSelector, createSelector} from '@ngrx/store';
import {TextCasesState} from '../reducers/text-cases.reducers';

// Selectors
export const selectTextCases = createFeatureSelector<TextCasesState>('textCases');

export const textToProcess = createSelector(selectTextCases, textCases => textCases?.textToProcess);
export const textResult = createSelector(selectTextCases, textCases => textCases?.textResult);
export const textFunction = createSelector(selectTextCases, textCases => textCases?.textFunction);

export const fileToUpload = createSelector(selectTextCases, textCases => textCases?.fileToUpload);

