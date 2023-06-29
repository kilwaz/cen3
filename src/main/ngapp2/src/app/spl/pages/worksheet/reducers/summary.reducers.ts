// Actions
import {SummaryActions, SummaryActionTypes} from "../actions/summary.actions";

export interface SummaryState {
  content: string;
}

export const initialSummaryState: SummaryState = {
  content: ''
};

export function summaryReducer(state = initialSummaryState, action: SummaryActions): SummaryState {
  switch (action.type) {
    case SummaryActionTypes.LoadSummary: {
      return {
        ...state
      };
    }
    case SummaryActionTypes.UpdateSummary: {
      return {
        ...state,
        content: action.payload.content
      };
    }
    default:
      return state;
  }
}
