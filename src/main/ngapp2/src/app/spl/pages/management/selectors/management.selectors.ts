// NGRX
import {createFeatureSelector, createSelector} from '@ngrx/store';
import {ManagementState} from "../reducers/management.reducers";

// Selectors
export const selectManagement = createFeatureSelector<ManagementState>('management');

export const totalMemory = createSelector(selectManagement, management => management?.totalMemory);
export const freeMemory = createSelector(selectManagement, management => management?.freeMemory);
export const maxMemory = createSelector(selectManagement, management => management?.maxMemory);

export const fileData = createSelector(selectManagement, management => management?.fileData);
export const fileUrl = createSelector(selectManagement, management => management?.fileUrl);

