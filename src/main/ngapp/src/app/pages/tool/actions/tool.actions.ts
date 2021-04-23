import {Action} from '@ngrx/store';

export enum ToolActionTypes {
  Test = '[Test] Action'
}

export class Test implements Action {
  readonly type = ToolActionTypes.Test;

  constructor(public payload: { message: string }) {
  }
}

export type ToolActions =
  Test;
