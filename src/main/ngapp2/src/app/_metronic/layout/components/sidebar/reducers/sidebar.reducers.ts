// Actions
import {SideBarActions, SideBarActionTypes} from '../actions/sidebar.actions';
import {MenuItem} from "../../../../../spl/wsObjects/menuItem";

export interface SideBarState {
  menuItem: Array<MenuItem>;
}

export const initialAuthState: SideBarState = {
  menuItem: null
};

export function sideBarReducer(state = initialAuthState, action: SideBarActions): SideBarState {
  switch (action.type) {
    case SideBarActionTypes.LoadRoles: {
      return {
        ...state,
        menuItem: action.payload.menuItems
      };
    }
    default:
      return state;
  }
}
