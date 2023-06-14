// Actions
import {HierarchyActions, HierarchyActionTypes} from '../actions/hierarchy.actions';
import {HierarchyListItem} from "../../../wsObjects/hierarchyListItem";

export interface HierarchyState {
  hierarchyItems: Array<HierarchyListItem>;
  selectedItem: HierarchyListItem;
}

export const initialAuthState: HierarchyState = {
  hierarchyItems: null,
  selectedItem: null
};

export function hierarchyReducer(state = initialAuthState, action: HierarchyActions): HierarchyState {
  switch (action.type) {
    case HierarchyActionTypes.LoadHierarchy: {
      return {
        ...state,
        hierarchyItems: action.payload.hierarchyItems
      };
    }
    case HierarchyActionTypes.ClickedHierarchy: {
      return {
        ...state,
        selectedItem: action.payload.selectedItem
      };
    }
    default:
      return state;
  }
}
