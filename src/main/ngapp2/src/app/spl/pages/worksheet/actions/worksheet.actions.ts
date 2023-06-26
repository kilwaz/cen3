import {Action} from '@ngrx/store';
import {WebRecord} from "../../../wsObjects/webRecord";
import {WebWorksheetConfig} from "../../../wsObjects/webWorksheetConfig";
import {SortItem} from "../../../wsObjects/sortItem";
import {SortFilter} from "../../../wsObjects/sortFilter";
import {WorksheetStatus} from "../../../wsObjects/worksheetStatus";

export enum WorksheetActionTypes {
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
    worksheetConfigs: Array<WebWorksheetConfig>,
    worksheetStatus: WorksheetStatus
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

export class AddSortItem implements Action {
  readonly type = WorksheetActionTypes.AddSortItem;

  constructor(public payload: {
    sort: SortItem
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
    sortFilter: SortFilter
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
    webRecord: WebRecord
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
