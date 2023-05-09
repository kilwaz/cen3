import {Component, OnInit, OnDestroy, Input} from '@angular/core';
import {Observable, Subscription} from 'rxjs';
import {
  currentSortFilterColumn,
  isSortFilterOpen,
  sortFilterPopupX,
  sortFilterPopupY
} from "../../selectors/worksheet.selectors";
import {select, Store} from "@ngrx/store";
import {WorksheetState} from "../../reducers/worksheet.reducers";
import {WebWorksheetConfig} from "../../../../wsObjects/webWorksheetConfig";
import {left} from "@popperjs/core";


@Component({
  selector: 'worksheet-sort-filter',
  templateUrl: './worksheet-sort-filter.component.html',
  styleUrls: ['./worksheet-sort-filter.component.scss'],
})
export class WorksheetSortFilterComponent implements OnInit, OnDestroy {
  // private fields
  private unsubscribe: Subscription[] = []; // Read more: => https://brianflove.com/2016/12/11/anguar-2-unsubscribe-observables/

  isSortFilterOpen$: Observable<boolean>;
  currentSortFilterColumn$: Observable<WebWorksheetConfig>;

  sortFilterPopupX$: Observable<any>;
  sortFilterPopupY$: Observable<any>;

  constructor(private store: Store<WorksheetState>) {
  }

  ngOnInit(): void {
    this.isSortFilterOpen$ = this.store.pipe(select(isSortFilterOpen));
    this.currentSortFilterColumn$ = this.store.pipe(select(currentSortFilterColumn));

    this.sortFilterPopupX$ = this.store.pipe(select(sortFilterPopupX));
    this.sortFilterPopupY$ = this.store.pipe(select(sortFilterPopupY));
  }

  ngOnDestroy() {
    // this.unsubscribe.forEach((sb) => sb.unsubscribe());
  }

  protected readonly left = left;
}
