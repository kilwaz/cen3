import {Action} from '@ngrx/store';

export enum ManagementActionTypes {
  QueryManagement = '[Management-QueryManagement] Action',
  ProcessResults = '[Management-ProcessResults] Action'
}

export class QueryManagement implements Action {
  readonly type = ManagementActionTypes.QueryManagement;

  constructor(public payload: {}) {
  }
}

export class ProcessResults implements Action {
  readonly type = ManagementActionTypes.ProcessResults;

  constructor(public payload: {
    totalMemory: number,
    freeMemory: number,
    maxMemory: number
  }) {
  }
}

export type ManagementActions =
  QueryManagement |
  ProcessResults;
