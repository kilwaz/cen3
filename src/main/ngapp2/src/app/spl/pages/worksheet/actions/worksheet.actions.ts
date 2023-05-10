import {Action} from '@ngrx/store';
import {WebRecord} from "../../../wsObjects/webRecord";
import {WebWorksheetConfig} from "../../../wsObjects/webWorksheetConfig";

export enum WorksheetActionTypes {
  RequestWorksheetData = '[Worksheet-RequestWorksheetData] Action',
  ProcessWorksheetData = '[Worksheet-ProcessWorksheetData] Action',
  ProcessFilteredListData = '[Worksheet-ProcessFilteredListData] Action',

  ToggleSortFilterPopup = '[Worksheet-ToggleSortFilterPopup] Action',
  SetSortFilterColumn = '[Worksheet-SetSortFilterColumn] Action',
  SetSortFilterPopupPosition = '[Worksheet-SetSortFilterPopupPosition] Action'
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
    worksheetRecords: Array<WebRecord>,
    worksheetConfigs: Array<WebWorksheetConfig>
  }) {
  }
}

export class ProcessFilteredListData implements Action {
  readonly type = WorksheetActionTypes.ProcessFilteredListData;

  constructor(public payload: {
    filteredList: Array<string>,
  }) {
  }
}

export class ToggleSortFilterPopup implements Action {
  readonly type = WorksheetActionTypes.ToggleSortFilterPopup;

  constructor(public payload: {
    isOpen: boolean
  }) {
  }
}

export class SetSortFilterColumn implements Action {
  readonly type = WorksheetActionTypes.SetSortFilterColumn;

  constructor(public payload: {
    currentSortFilterColumn: WebWorksheetConfig
  }) {
  }
}

export class SetSortFilterPopupPosition implements Action {
  readonly type = WorksheetActionTypes.SetSortFilterPopupPosition;

  constructor(public payload: {
    sortFilterPopupX: any,
    sortFilterPopupY: any
  }) {
  }
}

export type WorksheetActions =
  RequestWorksheetData |
  ProcessWorksheetData |
  ToggleSortFilterPopup |
  SetSortFilterColumn |
  SetSortFilterPopupPosition |
  ProcessFilteredListData;
