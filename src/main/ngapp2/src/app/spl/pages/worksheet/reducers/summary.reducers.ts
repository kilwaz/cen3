// Actions
import {SummaryActions, SummaryActionTypes} from "../actions/summary.actions";
import {ConfigurableUiDataItem} from "../../../wsObjects/configurableUiDataItem";

export interface SummaryState {
  content: string;
  configurableUi: Array<ConfigurableUiDataItem>
}

export const initialSummaryState: SummaryState = {
  content: '',
  configurableUi: undefined
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
        content: action.payload.content,
        configurableUi: action.payload.configurableUi
      };
    }
    default:
      return state;
  }
}
