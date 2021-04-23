// NGRX
import {createFeatureSelector, createSelector} from '@ngrx/store';
import {AppState} from '../../index';
import {ToolState} from '../reducers/tool.reducers';

// Selectors
export const selectTool = createFeatureSelector<AppState, ToolState>('tool');

export const message = createSelector(selectTool, tool => tool.message);
