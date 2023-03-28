// Actions
import {SideBarActions, SideBarActionTypes} from '../actions/sidebar.actions';
import {MenuItem} from "../../../../../spl/wsObjects/menuItem";

export interface SideBarState {
  username: string;
  menuItem: Array<MenuItem>;
}

export const initialAuthState: SideBarState = {
  username: 'None',
  menuItem: null
};

export function sideBarReducer(state = initialAuthState, action: SideBarActions): SideBarState {
  switch (action.type) {
    case SideBarActionTypes.LoadRoles: {
      const usernamePayload: string = action.payload.username;
      const menuPayload: Array<MenuItem> = action.payload.menuItems;

      return {
        ...state,
        username: usernamePayload,
        menuItem: menuPayload
      };
    }
    default:
      return state;
  }
}
