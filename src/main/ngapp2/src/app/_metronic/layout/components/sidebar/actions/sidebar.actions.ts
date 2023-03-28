import {Action} from '@ngrx/store';
import {Menu} from "../../../../../spl/wsActions/menu";
import {MenuItem} from "../../../../../spl/wsObjects/menuItem";

export enum SideBarActionTypes {
  LoadRoles = '[SideBar-LoadRoles] Action',
  RequestMenuLayout = '[SideBar-RequestMenu] Action'
}

export class LoadRoles implements Action {
  readonly type = SideBarActionTypes.LoadRoles;

  constructor(public payload: {
    username: string,
    menuItems: Array<MenuItem>
  }) {
  }
}

export class RequestMenuLayout implements Action {
  readonly type = SideBarActionTypes.RequestMenuLayout;

  constructor(public payload: {
  }) {
  }
}

export type SideBarActions =
  LoadRoles | RequestMenuLayout;
