import {Action, createFeatureSelector, createReducer, on} from '@ngrx/store';
import * as TestActions from '../action/test.action';

export interface TestState {
  testString: string;
}

export const initialState: TestState = {
  testString: "Sup"
};

const testReducer = createReducer(
  initialState,
  on(TestActions.testAction, (state, {testString}) => ({testString: testString})),
);

export function reducer(state: TestState | undefined, action: Action) {
  return testReducer(state, action);
}
