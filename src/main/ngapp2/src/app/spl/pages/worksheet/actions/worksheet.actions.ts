import {Action} from '@ngrx/store';
import {WebRecord} from "../../../wsObjects/webRecord";

export enum WorksheetActionTypes {
  RequestWorksheetData = '[Worksheet-RequestWorksheetData] Action',
  ProcessWorksheetData = '[Worksheet-ProcessWorksheetData] Action'
}

export class RequestWorksheetData implements Action {
  readonly type = WorksheetActionTypes.RequestWorksheetData;

  constructor(public payload: {
    requestID: string
  }) {
  }
}

export class ProcessWorksheetData implements Action {
  readonly type = WorksheetActionTypes.ProcessWorksheetData;

  constructor(public payload: {
    requestID: string,
    worksheetRecords: Array<WebRecord>
  }) {
  }
}

export type WorksheetActions =
  RequestWorksheetData |
  ProcessWorksheetData;
