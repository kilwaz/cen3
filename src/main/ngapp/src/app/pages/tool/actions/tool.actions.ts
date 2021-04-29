import {Action} from '@ngrx/store';

export enum ToolActionTypes {
  Test = '[Test] Action',
  StartDateChange = '[Tool-StartDateChange] Action',
  AgeOnDateChange = '[Tool-AgeOnDateChange] Action'
}

export class Test implements Action {
  readonly type = ToolActionTypes.Test;

  constructor(public payload: {
    ageDays: number,
    ageMonths: number,
    ageYears: number
  }) {
  }
}

export class StartDateChange implements Action {
  readonly type = ToolActionTypes.StartDateChange;

  constructor(public payload: { startDate: Date }) {
  }
}

export class AgeOnDateChange implements Action {
  readonly type = ToolActionTypes.AgeOnDateChange;

  constructor(public payload: { ageOnDate: Date }) {
  }
}

export type ToolActions =
  Test
  | StartDateChange
  | AgeOnDateChange;
