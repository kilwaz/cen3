// Actions
import {ToolActions, ToolActionTypes} from '../actions/tool.actions';

export interface ToolState {
  message: string;
}

export const initialAuthState: ToolState = {
  message: 'No Message sent yet',
};

export function toolReducer(state = initialAuthState, action: ToolActions): ToolState {
  switch (action.type) {
    case ToolActionTypes.Test: {
      const messagePayload: string = action.payload.message;

      return {
        ...state,
        message: messagePayload
      };
    }

    default:
      return state;
  }
}
