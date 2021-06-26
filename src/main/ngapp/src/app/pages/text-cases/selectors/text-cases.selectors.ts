// NGRX
import {createFeatureSelector, createSelector} from '@ngrx/store';
import {AppState} from '../../index';
import {TextCasesState} from '../reducers/text-cases.reducers';

// Selectors
export const selectTextCases = createFeatureSelector<AppState, TextCasesState>('textCases');

export const textToProcess = createSelector(selectTextCases, textCases => textCases?.textToProcess);
export const textResult = createSelector(selectTextCases, textCases => textCases?.textResult);
export const textFunction = createSelector(selectTextCases, textCases => textCases?.textFunction);

