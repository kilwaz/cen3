// Actions
import {DashboardActions, DashboardActionTypes} from '../actions/dashboard.actions';

export interface DashboardState {
  textToProcess: string;
}

export const initialAuthState: DashboardState = {
  textToProcess: ''
};

export function dashboardReducer(state = initialAuthState, action: DashboardActions): DashboardState {
  switch (action.type) {
    case DashboardActionTypes.ProcessText: {
      return {
        ...state,
        // textFunction: textFunctionPayload,
      };
    }
    default:
      return state;
  }
}
