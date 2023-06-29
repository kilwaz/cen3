// Actions
import {SideBarActions, SideBarActionTypes} from '../actions/sidebar.actions';
import {MenuItem} from "../../../../../spl/wsObjects/menuItem";
import {createEntityAdapter, EntityAdapter, EntityState} from "@ngrx/entity";
import {HierarchyListItem} from "../../../../../spl/wsObjects/hierarchyListItem";

export interface MenuState {
  menuItems: MenuItemsState;
}

export interface MenuItemsState extends EntityState<MenuItem> {
}

export const adapter: EntityAdapter<MenuItem> = createEntityAdapter<MenuItem>({
  selectId: menuItem => menuItem.title
});
export const initialMenuItemsState: MenuItemsState = adapter.getInitialState({});

export const initialMenuState: MenuState = {
  menuItems: initialMenuItemsState
};

export function sideBarReducer(state = initialMenuState, action: SideBarActions): MenuState {
  switch (action.type) {
    case SideBarActionTypes.LoadRoles: {
      return {
        ...state,
        menuItems: adapter.setAll(action.payload.menuItems, state.menuItems),
      };
    }
    case SideBarActionTypes.UpdateWorksheetLink: {
      return {
        ...state,
        menuItems: adapter.updateOne(action.payload.update, state.menuItems)
      };
    }
    default:
      return state;
  }
}
