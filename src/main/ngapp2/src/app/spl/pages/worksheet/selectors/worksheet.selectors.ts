// NGRX
import {createFeatureSelector, createSelector} from '@ngrx/store';
import {WorksheetState} from '../reducers/worksheet.reducers';

// Selectors
export const selectWorksheet = createFeatureSelector<WorksheetState>('worksheet');

export const requestID = createSelector(selectWorksheet, worksheet => worksheet?.requestID);
export const worksheetRecords = createSelector(selectWorksheet, worksheet => worksheet?.worksheetRecords);
export const worksheetConfigs = createSelector(selectWorksheet, worksheet => worksheet?.worksheetConfigs);

export const isSortFilterOpen = createSelector(selectWorksheet, worksheet => worksheet?.isSortFilterOpen);
export const currentSortFilterColumn = createSelector(selectWorksheet, worksheet => worksheet?.currentSortFilterColumn);

export const sortFilterPopupX = createSelector(selectWorksheet, worksheet => worksheet?.sortFilterPopupX);
export const sortFilterPopupY = createSelector(selectWorksheet, worksheet => worksheet?.sortFilterPopupY);

export const filteredList = createSelector(selectWorksheet, worksheet => worksheet?.filteredList);

export const sortFilter = createSelector(selectWorksheet, worksheet => worksheet?.sortFilter);

export const isActiveSort = (sortName: string) =>
  createSelector(
    sortFilter,
    (sortFilter) => {
      for (let i = 0; i < sortFilter.sorts.length; i++) {
        if (sortFilter.sorts[i].definitionName === sortName) {
          return sortFilter.sorts[i];
        }
      }
      return undefined;
    }
  );
