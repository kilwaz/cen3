// NGRX
import {createFeatureSelector, createSelector} from '@ngrx/store';
import {AppState} from '../../index';
import {ToolState} from '../reducers/tool.reducers';

// Selectors
export const selectTool = createFeatureSelector<AppState, ToolState>('tool');

export const ageDays = createSelector(selectTool, tool => {
  if (tool.ageDays === 0) {
    return '';
  } else if (tool.ageDays === 1) {
    return tool.ageDays + ' Day';
  } else {
    return tool.ageDays + ' Days';
  }
});

export const ageMonths = createSelector(selectTool, tool => {
  if (tool.ageMonths === 0) {
    return '';
  } else if (tool.ageMonths === 1) {
    return tool.ageMonths + ' Month';
  } else {
    return tool.ageMonths + ' Months';
  }
});

export const ageYears = createSelector(selectTool, tool => {
  if (tool.ageYears === 0) {
    return '';
  } else if (tool.ageYears === 1) {
    return tool.ageYears + ' Year';
  } else {
    return tool.ageYears + ' Years';
  }
});

export const startDate = createSelector(selectTool, tool => tool.startDate);
export const ageOnDate = createSelector(selectTool, tool => tool.ageOnDate);

