import {Injectable} from '@angular/core';
import {SortItem} from "../../../wsObjects/sortItem";
import {SortFilter} from "../../../wsObjects/sortFilter";
import {Store} from "@ngrx/store";
import {WorksheetState} from "../reducers/worksheet.reducers";
import {UpdateSortFilter} from "../actions/worksheet.actions";

@Injectable()
export class SortFilterService {
  constructor(private store: Store<WorksheetState>) {

  }

  addSort(sortItem: SortItem, sortFilter: SortFilter): void {
    let sortFilterCloned: SortFilter = new SortFilter();
    // Re-add the sorts but don't include ones that already exist for this reference
    for (let i = 0; i < sortFilter.sorts.length; i++) {
      if (sortFilter.sorts[i].definitionName !== sortItem.definitionName) {
        sortFilterCloned.sorts.push(sortFilter.sorts[i]);
      }
    }
    sortFilterCloned.sorts.push(sortItem);

    this.store.dispatch(new UpdateSortFilter({
      sortFilter: sortFilterCloned
    }));
  }

  removeSort(sortReference: string, sortFilter: SortFilter): void {
    let sortFilterCloned: SortFilter = new SortFilter();
    for (let i = 0; i < sortFilter.sorts.length; i++) {
      if (sortFilter.sorts[i].definitionName !== sortReference) {
        sortFilterCloned.sorts.push(sortFilter.sorts[i]);
      }
    }

    this.store.dispatch(new UpdateSortFilter({
      sortFilter: sortFilterCloned
    }));
  }
}
