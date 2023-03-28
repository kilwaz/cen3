// Actions
import {SideBarActions, SideBarActionTypes} from '../actions/sidebar.actions';

export interface SideBarState {
  username: string;
}

export const initialAuthState: SideBarState = {
  username: 'none'
};

export function sideBarReducer(state = initialAuthState, action: SideBarActions): SideBarState {
  switch (action.type) {
    case SideBarActionTypes.LoadRoles: {
      const usernamePayload: string = action.payload.username;

      return {
        ...state,
        username: usernamePayload
      };
    }
    default:
      return state;
  }
}
