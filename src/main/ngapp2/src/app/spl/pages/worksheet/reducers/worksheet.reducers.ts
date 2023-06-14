// Actions
import {WorksheetActions, WorksheetActionTypes} from '../actions/worksheet.actions';
import {WebRecord} from "../../../wsObjects/webRecord";
import {WebWorksheetConfig} from "../../../wsObjects/webWorksheetConfig";
import {SortFilter} from "../../../wsObjects/sortFilter";

export interface WorksheetState {
  requestID: string;
  worksheetRecords: Array<WebRecord>
  worksheetConfigs: Array<WebWorksheetConfig>,

  isSortFilterOpen: boolean,
  currentSortFilterColumn: WebWorksheetConfig,
  sortFilterPopupX: any,
  sortFilterPopupY: any,
  filteredList: Array<string>,

  sortFilter: SortFilter
}

export const initialAuthState: WorksheetState = {
  requestID: '',
  worksheetRecords: undefined,
  worksheetConfigs: undefined,

  isSortFilterOpen: false,
  currentSortFilterColumn: undefined,
  sortFilterPopupX: 0,
  sortFilterPopupY: 0,
  filteredList: undefined,

  sortFilter: new SortFilter()
};

export function worksheetReducer(state = initialAuthState, action: WorksheetActions): WorksheetState {
  switch (action.type) {
    case WorksheetActionTypes.RequestWorksheetData: {
      return {
        ...state,
        requestID: action.payload.requestID
      };
    }
    case WorksheetActionTypes.ProcessWorksheetData: {
      return {
        ...state,
        requestID: action.payload.requestID,
        worksheetRecords: action.payload.worksheetRecords,
        worksheetConfigs: action.payload.worksheetConfigs
      };
    }
    case WorksheetActionTypes.ProcessFilteredListData: {
      return {
        ...state,
        filteredList: action.payload.filteredList,
      };
    }
    case WorksheetActionTypes.ToggleSortFilterPopup: {
      return {
        ...state,
        isSortFilterOpen: action.payload.isOpen
      };
    }
    case WorksheetActionTypes.SetSortFilterColumn: {
      return {
        ...state,
        currentSortFilterColumn: action.payload.currentSortFilterColumn
      };
    }
    case WorksheetActionTypes.SetSortFilterPopupPosition: {
      return {
        ...state,
        sortFilterPopupX: action.payload.sortFilterPopupX,
        sortFilterPopupY: action.payload.sortFilterPopupY
      };
    }
    case WorksheetActionTypes.UpdateSortFilter: {
      return {
        ...state,
        sortFilter: action.payload.sortFilter
      };
    }
    case WorksheetActionTypes.ApplyUpdate: {
      return {
        ...state,
        worksheetRecords: state.worksheetRecords.map(worksheetRecord => worksheetRecord.uuid === action.payload.recordUUID ?
          action.payload.webRecord : worksheetRecord)
      };
    }
    default:
      return state;
  }
}
