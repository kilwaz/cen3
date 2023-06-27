// Actions
import {HierarchyActions, HierarchyActionTypes} from '../actions/hierarchy.actions';
import {HierarchyListItem} from "../../../wsObjects/hierarchyListItem";
import {createEntityAdapter, EntityAdapter, EntityState} from '@ngrx/entity';

export interface HierarchyState {
  hierarchyItems: HierarchyItemsState;
  selectedItem: HierarchyListItem;
}

export interface HierarchyItemsState extends EntityState<HierarchyListItem> {
}

export const adapter: EntityAdapter<HierarchyListItem> = createEntityAdapter<HierarchyListItem>({
  selectId: hierarchyItem => hierarchyItem.nodeReference
});
export const initialState: HierarchyItemsState = adapter.getInitialState({});


export const initialAuthState: HierarchyState = {
  hierarchyItems: initialState,
  selectedItem: null
};

export function hierarchyReducer(state = initialAuthState, action: HierarchyActions): HierarchyState {
  switch (action.type) {
    case HierarchyActionTypes.LoadHierarchy: {
      return {
        ...state,
        hierarchyItems: adapter.setAll(action.payload.hierarchyItems, state.hierarchyItems)
      };
    }
    case HierarchyActionTypes.ExpandCollapseHierarchy: {
      return {
        ...state,
        hierarchyItems: adapter.updateOne(action.payload.update, state.hierarchyItems)
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
