import {Action} from '@ngrx/store';

export enum ManagementActionTypes {
  QueryManagement = '[Management-QueryManagement] Action',
  ProcessResults = '[Management-ProcessResults] Action',

  DownloadTestRequest = '[Management-DownloadTestRequest] Action'
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

export class DownloadTestRequest implements Action {
  readonly type = ManagementActionTypes.DownloadTestRequest;

  constructor(public payload: {}) {
  }
}

export type ManagementActions =
  QueryManagement |
  ProcessResults |

  DownloadTestRequest;
