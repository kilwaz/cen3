import {Action} from '@ngrx/store';
import {WebRecordDataItem} from "../../../wsObjects/webRecordDataItem";
import {WebWorksheetConfigDataItem} from "../../../wsObjects/webWorksheetConfigDataItem";
import {SortDataItem} from "../../../wsObjects/sortDataItem";
import {SortFilterDataItem} from "../../../wsObjects/sortFilterDataItem";
import {WorksheetStatusDataItem} from "../../../wsObjects/worksheetStatusDataItem";

export enum WorksheetActionTypes {
  SetCurrentWorksheet = '[Worksheet-SetCurrentWorksheet] Action',
  RequestWorksheetData = '[Worksheet-RequestWorksheetData] Action',
  ProcessWorksheetData = '[Worksheet-ProcessWorksheetData] Action',
  ProcessFilteredListData = '[Worksheet-ProcessFilteredListData] Action',

  ToggleSortFilterPopup = '[Worksheet-ToggleSortFilterPopup] Action',
  SetSortFilterColumn = '[Worksheet-SetSortFilterColumn] Action',
  SetSortFilterPopupPosition = '[Worksheet-SetSortFilterPopupPosition] Action',

  AddSortItem = '[Worksheet-AddSortItem] Action',
  RemoveSortItem = '[Worksheet-RemoveSortItem] Action',
  ClearSort = '[Worksheet-ClearSort] Action',
  UpdateSortFilter = '[Worksheet-UpdateSortFilter] Action',

  Update = '[Worksheet-Update] Action',
  ApplyUpdate = '[Worksheet-ApplyUpdate] Action',

  PaginationPageSizeChange = '[Worksheet-PaginationPageSizeChange] Action',
  PaginationPageNumberChange = '[Worksheet-PaginationPageNumberChange] Action'
}

export class SetCurrentWorksheet implements Action {
  readonly type = WorksheetActionTypes.SetCurrentWorksheet;

  constructor(public payload: {
    nodeReference: string
  }) {
  }
}

export class RequestWorksheetData implements Action {
  readonly type = WorksheetActionTypes.RequestWorksheetData;

  constructor(public payload: {}) {
  }
}

export class ProcessWorksheetData implements Action {
  readonly type = WorksheetActionTypes.ProcessWorksheetData;

  constructor(public payload: {
    nodeReference: string,
    worksheetRecords: Array<WebRecordDataItem>,
    worksheetConfigs: Array<WebWorksheetConfigDataItem>,
    worksheetStatus: WorksheetStatusDataItem
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
    currentSortFilterColumn: WebWorksheetConfigDataItem
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

export class AddSortItem implements Action {
  readonly type = WorksheetActionTypes.AddSortItem;

  constructor(public payload: {
    sort: SortDataItem
  }) {
  }
}

export class RemoveSortItem implements Action {
  readonly type = WorksheetActionTypes.RemoveSortItem;

  constructor(public payload: {
    sortReference: string
  }) {
  }
}

export class ClearSort implements Action {
  readonly type = WorksheetActionTypes.ClearSort;

  constructor(public payload: {}) {
  }
}

export class UpdateSortFilter implements Action {
  readonly type = WorksheetActionTypes.UpdateSortFilter;

  constructor(public payload: {
    sortFilter: SortFilterDataItem
  }) {
  }
}

export class Update implements Action {
  readonly type = WorksheetActionTypes.Update;

  constructor(public payload: {
    value: string,
    recordUUID: string,
    definitionName: string,
    updateSource: string
  }) {
  }
}

export class ApplyUpdate implements Action {
  readonly type = WorksheetActionTypes.ApplyUpdate;

  constructor(public payload: {
    recordUUID: string,
    webRecord: WebRecordDataItem
  }) {
  }
}

export class PaginationPageSizeChange implements Action {
  readonly type = WorksheetActionTypes.PaginationPageSizeChange;

  constructor(public payload: {
    pageSize: number
  }) {
  }
}

export class PaginationPageNumberChange implements Action {
  readonly type = WorksheetActionTypes.PaginationPageNumberChange;

  constructor(public payload: {
    currentPageNumber: number
  }) {
  }
}

export type WorksheetActions =
  SetCurrentWorksheet |
  RequestWorksheetData |
  ProcessWorksheetData |
  ToggleSortFilterPopup |

  SetSortFilterColumn |
  SetSortFilterPopupPosition |
  ProcessFilteredListData |

  AddSortItem |
  RemoveSortItem |
  ClearSort |
  UpdateSortFilter |

  Update |
  ApplyUpdate |

  PaginationPageSizeChange |
  PaginationPageNumberChange;
