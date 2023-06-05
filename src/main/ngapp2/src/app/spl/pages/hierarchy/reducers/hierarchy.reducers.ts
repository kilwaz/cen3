// Actions
import {HierarchyActions, HierarchyActionTypes} from '../actions/hierarchy.actions';

export interface HierarchyState {
  textToProcess: string;
}

export const initialAuthState: HierarchyState = {
  textToProcess: ''
};

export function hierarchyReducer(state = initialAuthState, action: HierarchyActions): HierarchyState {
  switch (action.type) {
    case HierarchyActionTypes.ProcessText: {
      return {
        ...state,
        // textFunction: textFunctionPayload,
      };
    }
    default:
      return state;
  }
}
