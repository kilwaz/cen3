import {Action} from '@ngrx/store';

export enum SideBarActionTypes {
  LoadRoles = '[SideBar-LoadRoles] Action'
}

export class LoadRolesAction implements Action {
  readonly type = SideBarActionTypes.LoadRoles;

  constructor(public payload: {
    username: string
  }) {
  }
}

export type SideBarActions =
  LoadRolesAction;
