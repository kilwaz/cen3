// Actions
import {WorksheetActions, WorksheetActionTypes} from '../actions/worksheet.actions';
import {WebRecord} from "../../../wsObjects/webRecord";

export interface WorksheetState {
  requestID: string;
  worksheetRecords: Array<WebRecord>
}

export const initialAuthState: WorksheetState = {
  requestID: '',
  worksheetRecords: undefined
};

export function worksheetReducer(state = initialAuthState, action: WorksheetActions): WorksheetState {
  switch (action.type) {
    case WorksheetActionTypes.RequestWorksheetData: {
      const requestIDPayload: string = action.payload.requestID;

      return {
        ...state,
        requestID: requestIDPayload
      };
    }
    case WorksheetActionTypes.ProcessWorksheetData: {
      const requestIDPayload: string = action.payload.requestID;
      const worksheetRecordsPayload: Array<WebRecord> = action.payload.worksheetRecords;

      return {
        ...state,
        requestID: requestIDPayload,
        worksheetRecords: worksheetRecordsPayload
      };
    }
    default:
      return state;
  }
}
