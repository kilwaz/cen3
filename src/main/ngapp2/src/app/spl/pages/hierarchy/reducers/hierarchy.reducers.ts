// Actions
import {HierarchyActions, HierarchyActionTypes} from '../actions/hierarchy.actions';
import {HierarchyListItem} from "../../../wsObjects/hierarchyListItem";
import {createEntityAdapter, EntityAdapter, EntityState} from '@ngrx/entity';

export interface HierarchyState {
  hierarchyItems: HierarchyItemsState;
  selectedItem: HierarchyListItem;

  reloadHierarchy: boolean;
}

export interface HierarchyItemsState extends EntityState<HierarchyListItem> {
}

export const adapter: EntityAdapter<HierarchyListItem> = createEntityAdapter<HierarchyListItem>({
  selectId: hierarchyItem => hierarchyItem.nodeReference
});
export const initialHierarchyItemsState: HierarchyItemsState = adapter.getInitialState({});


export const initialHierarchyState: HierarchyState = {
  hierarchyItems: initialHierarchyItemsState,
  selectedItem: null,

  reloadHierarchy: true
};

export function hierarchyReducer(state = initialHierarchyState, action: HierarchyActions): HierarchyState {
  switch (action.type) {
    case HierarchyActionTypes.LoadHierarchy: {
      return {
        ...state,
        hierarchyItems: adapter.setAll(action.payload.hierarchyItems, state.hierarchyItems),
        reloadHierarchy: false
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
