import {Action} from '@ngrx/store';
import {HierarchyListItem} from "../../../wsObjects/hierarchyListItem";

export enum HierarchyActionTypes {
  RequestHierarchy = '[Hierarchy-RequestHierarchy] Action',
  LoadHierarchy = '[Hierarchy-LoadHierarchy] Action'
}

export class LoadHierarchy implements Action {
  readonly type = HierarchyActionTypes.LoadHierarchy;

  constructor(public payload: {
    hierarchyItems: Array<HierarchyListItem>
  }) {
  }
}

export class RequestHierarchy implements Action {
  readonly type = HierarchyActionTypes.RequestHierarchy;

  constructor(public payload: {}) {
  }
}

export type HierarchyActions =
  LoadHierarchy |
  RequestHierarchy;
