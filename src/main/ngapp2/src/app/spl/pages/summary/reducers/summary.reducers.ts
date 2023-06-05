// Actions
import {SummaryActions, SummaryActionTypes} from '../actions/summary.actions';

export interface SummaryState {
  textToProcess: string;
}

export const initialAuthState: SummaryState = {
  textToProcess: ''
};

export function summaryReducer(state = initialAuthState, action: SummaryActions): SummaryState {
  switch (action.type) {
    case SummaryActionTypes.ProcessText: {
      return {
        ...state,
        // textFunction: textFunctionPayload,
      };
    }
    default:
      return state;
  }
}
