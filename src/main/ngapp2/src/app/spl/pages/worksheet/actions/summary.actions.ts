import {Action} from '@ngrx/store';

export enum SummaryActionTypes {
  LoadSummary = '[Worksheet-LoadSummary] Action',
  UpdateSummary = '[Worksheet-UpdateSummary] Action'
}

export class LoadSummary implements Action {
  readonly type = SummaryActionTypes.LoadSummary;

  constructor(public payload: {}) {
  }
}

export class UpdateSummary implements Action {
  readonly type = SummaryActionTypes.UpdateSummary;

  constructor(public payload: {
    content: string
  }) {
  }
}

export type SummaryActions =
  LoadSummary |
  UpdateSummary;
