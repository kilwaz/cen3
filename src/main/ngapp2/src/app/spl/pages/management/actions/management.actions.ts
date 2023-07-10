import {Action} from '@ngrx/store';

export enum ManagementActionTypes {
  QueryManagement = '[Management-QueryManagement] Action',
  ProcessResults = '[Management-ProcessResults] Action',

  DownloadTestRequest = '[Management-DownloadTestRequest] Action',
  DownloadConfigRequest = '[Management-DownloadConfigRequest] Action',

  ProcessDownloadedFile = '[Management-ProcessDownloadedFile] Action',

  RecalculateHierarchy = '[Management-RecalculateHierarchy] Action'
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

export class DownloadConfigRequest implements Action {
  readonly type = ManagementActionTypes.DownloadConfigRequest;

  constructor(public payload: {}) {
  }
}

export class ProcessDownloadedFile implements Action {
  readonly type = ManagementActionTypes.ProcessDownloadedFile;

  constructor(public payload: {
    content: string,
    fileName: string
  }) {
  }
}

export class RecalculateHierarchy implements Action {
  readonly type = ManagementActionTypes.RecalculateHierarchy;

  constructor(public payload: {}) {
  }
}

export type ManagementActions =
  QueryManagement |
  ProcessResults |

  DownloadTestRequest |
  DownloadConfigRequest |
  ProcessDownloadedFile |

  RecalculateHierarchy;
