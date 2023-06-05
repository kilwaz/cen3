import {Action} from '@ngrx/store';

export enum HierarchyActionTypes {
  ProcessText = '[Hierarchy-ProcessText] Action'
}

export class ProcessText implements Action {
  readonly type = HierarchyActionTypes.ProcessText;

  constructor(public payload: {
    textFunction: number
  }) {
  }
}

export type HierarchyActions =
  ProcessText;
