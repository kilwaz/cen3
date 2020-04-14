import {Action, createReducer, on} from '@ngrx/store';
import * as TestActions from '../action/ngrxtest.action';
import {RecordContainer} from "../containers/recordContainer";

export interface RecordState {
  testString?: string;
  records?: RecordContainer[];
}

export const initialState: RecordState = {
  testString: null,
  records: null
};

const loadRecordComplete = createReducer(
  initialState,
  on(TestActions.LoadRecordSuccessAction, (state, {payload}) => ({
    ...state,
    records: payload,
    testString: "Completed!"
  })),
  on(TestActions.NgrxtestAction, (state, {testString}) => ({
    ...state,
    testString: testString
  })),
);

export function reducer(state: RecordState | undefined, action: Action) {
  return loadRecordComplete(state, action);
}
