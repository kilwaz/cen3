import {Action} from '@ngrx/store';

export enum SummaryActionTypes {
  ProcessText = '[Summary-ProcessText] Action'
}

export class ProcessText implements Action {
  readonly type = SummaryActionTypes.ProcessText;

  constructor(public payload: {
    textFunction: number
  }) {
  }
}

export type SummaryActions =
  ProcessText;
