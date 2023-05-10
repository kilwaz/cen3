import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {
  currentSortFilterColumn, filteredList,
  isSortFilterOpen,
  sortFilterPopupX,
  sortFilterPopupY
} from "../../selectors/worksheet.selectors";
import {select, Store} from "@ngrx/store";
import {WorksheetState} from "../../reducers/worksheet.reducers";
import {WebWorksheetConfig} from "../../../../wsObjects/webWorksheetConfig";

@Component({
  selector: 'worksheet-sort-filter',
  templateUrl: './worksheet-sort-filter.component.html',
  styleUrls: ['./worksheet-sort-filter.component.scss'],
})
export class WorksheetSortFilterComponent implements OnInit {
  isSortFilterOpen$: Observable<boolean>;
  currentSortFilterColumn$: Observable<WebWorksheetConfig>;

  sortFilterPopupX$: Observable<any>;
  sortFilterPopupY$: Observable<any>;

  filteredList$: Observable<Array<string>>;

  constructor(private store: Store<WorksheetState>) {
  }

  ngOnInit(): void {
    this.isSortFilterOpen$ = this.store.pipe(select(isSortFilterOpen));
    this.currentSortFilterColumn$ = this.store.pipe(select(currentSortFilterColumn));

    this.sortFilterPopupX$ = this.store.pipe(select(sortFilterPopupX));
    this.sortFilterPopupY$ = this.store.pipe(select(sortFilterPopupY));

    this.filteredList$ = this.store.pipe(select(filteredList));
  }
}
