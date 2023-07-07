// Actions
import {SetCurrentWorksheet, WorksheetActions, WorksheetActionTypes} from '../actions/worksheet.actions';
import {WebRecordDataItem} from "../../../wsObjects/webRecordDataItem";
import {WebWorksheetConfigDataItem} from "../../../wsObjects/webWorksheetConfigDataItem";
import {SortFilterDataItem} from "../../../wsObjects/sortFilterDataItem";
import {WorksheetStatusDataItem} from "../../../wsObjects/worksheetStatusDataItem";

export interface WorksheetState {
  nodeReference: string;
  worksheetRecords: Array<WebRecordDataItem>
  worksheetConfigs: Array<WebWorksheetConfigDataItem>,

  isSortFilterOpen: boolean,
  currentSortFilterColumn: WebWorksheetConfigDataItem,
  sortFilterPopupX: any,
  sortFilterPopupY: any,
  filteredList: Array<string>,

  sortFilter: SortFilterDataItem,
  worksheetStatus: WorksheetStatusDataItem
}

export const initialAuthState: WorksheetState = {
  nodeReference: '',
  worksheetRecords: undefined,
  worksheetConfigs: undefined,

  isSortFilterOpen: false,
  currentSortFilterColumn: undefined,
  sortFilterPopupX: 0,
  sortFilterPopupY: 0,
  filteredList: undefined,

  sortFilter: new SortFilterDataItem(),
  worksheetStatus: new WorksheetStatusDataItem()
};

export function worksheetReducer(state = initialAuthState, action: WorksheetActions): WorksheetState {
  switch (action.type) {
    case WorksheetActionTypes.SetCurrentWorksheet: {
      return {
        ...state,
        nodeReference: action.payload.nodeReference
      };
    }
    case WorksheetActionTypes.ProcessWorksheetData: {
      return {
        ...state,
        nodeReference: action.payload.nodeReference,
        worksheetRecords: action.payload.worksheetRecords,
        worksheetConfigs: action.payload.worksheetConfigs,
        worksheetStatus: new WorksheetStatusDataItem().wsFill(action.payload.worksheetStatus)
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
    case WorksheetActionTypes.PaginationPageSizeChange: {
      let worksheetStatus: WorksheetStatusDataItem = new WorksheetStatusDataItem().wsFill(state.worksheetStatus);
      worksheetStatus.pageSize = action.payload.pageSize;

      return {
        ...state,
        worksheetStatus: worksheetStatus
      }
    }
    case WorksheetActionTypes.PaginationPageNumberChange: {
      let worksheetStatus: WorksheetStatusDataItem = new WorksheetStatusDataItem().wsFill(state.worksheetStatus);
      worksheetStatus.currentPageNumber = action.payload.currentPageNumber;

      return {
        ...state,
        worksheetStatus: worksheetStatus
      }
    }
    default:
      return state;
  }
}
