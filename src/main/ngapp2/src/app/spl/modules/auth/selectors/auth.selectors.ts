// NGRX
import {createFeatureSelector, createSelector} from '@ngrx/store';
import {LoginResultState} from "../reducers/auth.reducers";

// Selectors
export const selectAuth = createFeatureSelector<LoginResultState>('auth');

export const username = createSelector(selectAuth, auth => auth?.username);
export const password = createSelector(selectAuth, auth => auth?.password);
export const errorMessage = createSelector(selectAuth, auth => auth?.errorMessage);
