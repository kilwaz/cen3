// NGRX
import {createFeatureSelector, createSelector} from '@ngrx/store';
import {adapter, MenuState} from "../reducers/sidebar.reducers";

// Selectors
export const selectRoles = createFeatureSelector<MenuState>('sideBar');

export const menuItems = createSelector(selectRoles, sideBar => sideBar?.menuItems);

export const {
  selectAll,
  selectEntities,
  selectIds,
  selectTotal
} = adapter.getSelectors();

