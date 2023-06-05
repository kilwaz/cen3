import {Action} from '@ngrx/store';

export enum DashboardActionTypes {
  ProcessText = '[Dashboard-ProcessText] Action'
}

export class ProcessText implements Action {
  readonly type = DashboardActionTypes.ProcessText;

  constructor(public payload: {
    textFunction: number
  }) {
  }
}

export type DashboardActions =
  ProcessText;
