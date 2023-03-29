// NGRX
import {createFeatureSelector, createSelector} from '@ngrx/store';
import {SideBarState} from "../reducers/sidebar.reducers";

// Selectors
export const selectRoles = createFeatureSelector<SideBarState>('sideBar');

export const menuItems = createSelector(selectRoles, sideBar => sideBar?.menuItem);

