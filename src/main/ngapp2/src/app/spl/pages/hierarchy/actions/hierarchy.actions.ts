import {Action} from '@ngrx/store';
import {HierarchyListItem} from "../../../wsObjects/hierarchyListItem";

export enum HierarchyActionTypes {
  RequestHierarchy = '[Hierarchy-RequestHierarchy] Action',
  LoadHierarchy = '[Hierarchy-LoadHierarchy] Action',
  ClickedHierarchy = '[Hierarchy-ClickedHierarchy] Action',
  WorksheetLinkClicked = '[Hierarchy-WorksheetLinkClicked] Action',

  ExpandCollapseHierarchy = '[Hierarchy-ExpandCollapseHierarchy] Action'
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

export class ClickedHierarchy implements Action {
  readonly type = HierarchyActionTypes.ClickedHierarchy;

  constructor(public payload: {
    selectedItem: HierarchyListItem
  }) {
  }
}

export class WorksheetLinkClicked implements Action {
  readonly type = HierarchyActionTypes.WorksheetLinkClicked;

  constructor(public payload: {
    worksheetId: string,
    worksheetName: string
  }) {
  }
}

export class ExpandCollapseHierarchy implements Action {
  readonly type = HierarchyActionTypes.ExpandCollapseHierarchy;

  constructor(public payload: {
    update: { id: string, changes: Partial<HierarchyListItem> }
  }) {
  }
}

export type HierarchyActions =
  LoadHierarchy |
  RequestHierarchy |
  ClickedHierarchy |
  ExpandCollapseHierarchy |
  WorksheetLinkClicked;
