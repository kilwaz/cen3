// Actions
import {HierarchyActions, HierarchyActionTypes} from '../actions/hierarchy.actions';
import {HierarchyListDataItem} from "../../../wsObjects/hierarchyListDataItem";
import {createEntityAdapter, EntityAdapter, EntityState} from '@ngrx/entity';

export interface HierarchyState {
  hierarchyItems: HierarchyItemsState;
  selectedItem: HierarchyListDataItem;

  reloadHierarchy: boolean;
}

export interface HierarchyItemsState extends EntityState<HierarchyListDataItem> {
}

export const adaptor: EntityAdapter<HierarchyListDataItem> = createEntityAdapter<HierarchyListDataItem>({
  selectId: hierarchyItem => hierarchyItem.nodeReference
});
export const initialHierarchyItemsState: HierarchyItemsState = adaptor.getInitialState({});


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
        hierarchyItems: adaptor.setAll(action.payload.hierarchyItems, state.hierarchyItems),
        reloadHierarchy: false
      };
    }
    case HierarchyActionTypes.ExpandCollapseHierarchy: {
      return {
        ...state,
        hierarchyItems: adaptor.updateOne(action.payload.update, state.hierarchyItems)
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
