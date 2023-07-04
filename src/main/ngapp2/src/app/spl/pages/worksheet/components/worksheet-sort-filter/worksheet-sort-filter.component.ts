import {Component, OnDestroy, OnInit} from '@angular/core';
import {Observable, Subscription} from 'rxjs';
import {
  currentSortFilterColumn,
  filteredList,
  isActiveSort,
  isSortFilterOpen,
  sortFilter,
  sortFilterPopupX,
  sortFilterPopupY
} from "../../selectors/worksheet.selectors";
import {select, Store} from "@ngrx/store";
import {WorksheetState} from "../../reducers/worksheet.reducers";
import {WebWorksheetConfigDataItem} from "../../../../wsObjects/webWorksheetConfigDataItem";
import {SortFilterDataItem} from "../../../../wsObjects/sortFilterDataItem";
import {AddSortItem, RemoveSortItem, ToggleSortFilterPopup} from "../../actions/worksheet.actions";
import {SortDataItem} from "../../../../wsObjects/sortDataItem";
import {switchMap} from "rxjs/operators";

@Component({
  selector: 'worksheet-sort-filter',
  templateUrl: './worksheet-sort-filter.component.html',
  styleUrls: ['./worksheet-sort-filter.component.scss'],
})
export class WorksheetSortFilterComponent implements OnInit, OnDestroy {
  // Observables
  isSortFilterOpen$: Observable<boolean>;
  currentSortFilterColumn$: Observable<WebWorksheetConfigDataItem>;

  sortFilterPopupX$: Observable<any>;
  sortFilterPopupY$: Observable<any>;

  filteredList$: Observable<Array<string>>;

  sortFilter$: Observable<SortFilterDataItem>;

  // Derived variables
  currentSortFilterColumn: WebWorksheetConfigDataItem;
  isActiveSort: boolean;
  activeSortDirection: string;
  sortFilter: SortFilterDataItem;

  // Unsubscribe tracker
  private unsubscribe: Subscription[] = [];

  constructor(private store: Store<WorksheetState>) {
  }

  ngOnInit(): void {
    this.isSortFilterOpen$ = this.store.pipe(select(isSortFilterOpen));
    this.currentSortFilterColumn$ = this.store.pipe(select(currentSortFilterColumn));
    this.unsubscribe.push(
      this.currentSortFilterColumn$.subscribe(webWorksheetConfig => this.currentSortFilterColumn = webWorksheetConfig)
    );
    this.unsubscribe.push(
      this.currentSortFilterColumn$.pipe(
        switchMap(currentSortFilterColumn => this.store.pipe(select(isActiveSort(currentSortFilterColumn?.definitionName))))
      ).subscribe(result => {
        this.isActiveSort = (result !== undefined);
        if (result !== undefined) {
          this.activeSortDirection = result.direction;
        }
      })
    );

    this.sortFilterPopupX$ = this.store.pipe(select(sortFilterPopupX));
    this.sortFilterPopupY$ = this.store.pipe(select(sortFilterPopupY));

    this.filteredList$ = this.store.pipe(select(filteredList));

    this.sortFilter$ = this.store.pipe(select(sortFilter));
    this.unsubscribe.push(
      this.sortFilter$.subscribe(sortFilter => this.sortFilter = sortFilter)
    );
  }

  addSort(direction: string): void {
    let sortItem = new SortDataItem();
    sortItem.definitionName = this.currentSortFilterColumn.definitionName;
    sortItem.direction = direction;

    this.store.dispatch(new AddSortItem({
      sort: sortItem
    }));
    this.store.dispatch(new ToggleSortFilterPopup({
      isOpen: false
    }));
  }

  removeSort(): void {
    this.store.dispatch(new RemoveSortItem({
      sortReference: this.currentSortFilterColumn.definitionName
    }));

    this.store.dispatch(new ToggleSortFilterPopup({
      isOpen: false
    }));
  }

  okClicked(): void {
    this.store.dispatch(new ToggleSortFilterPopup({
      isOpen: false
    }));
  }

  ngOnDestroy() {
    this.unsubscribe.forEach((sb) => sb.unsubscribe());
  }
}
