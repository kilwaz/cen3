import {Action} from '@ngrx/store';
import {MenuItem} from "../../../../../spl/wsObjects/menuItem";

export enum SideBarActionTypes {
  LoadRoles = '[SideBar-LoadRoles] Action',
  RequestMenuLayout = '[SideBar-RequestMenu] Action',

  UpdateWorksheetLink = '[SideBar-UpdateWorksheetLink] Action'
}

export class LoadRoles implements Action {
  readonly type = SideBarActionTypes.LoadRoles;

  constructor(public payload: {
    menuItems: Array<MenuItem>
  }) {
  }
}

export class RequestMenuLayout implements Action {
  readonly type = SideBarActionTypes.RequestMenuLayout;

  constructor(public payload: {}) {
  }
}

export class UpdateWorksheetLink implements Action {
  readonly type = SideBarActionTypes.UpdateWorksheetLink;

  constructor(public payload: {
    update: { id: string, changes: Partial<MenuItem> }
  }) {
  }
}

export type SideBarActions =
  LoadRoles |
  RequestMenuLayout |
  UpdateWorksheetLink;
