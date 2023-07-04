import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Observable, Subscription} from 'rxjs';
import {WebWorksheetConfigDataItem} from "../../../../wsObjects/webWorksheetConfigDataItem";
import {select, Store} from "@ngrx/store";
import {WorksheetState} from "../../reducers/worksheet.reducers";
import {worksheetStatus} from "../../selectors/worksheet.selectors";
import {WorksheetStatusDataItem} from "../../../../wsObjects/worksheetStatusDataItem";
import {PaginationPageNumberChange, PaginationPageSizeChange} from "../../actions/worksheet.actions";

@Component({
  selector: 'worksheet-pagination',
  templateUrl: './worksheet-pagination.component.html',
  styleUrls: ['./worksheet-pagination.component.scss'],
})
export class WorksheetPaginationComponent implements OnInit, OnDestroy {
  @Input('webWorksheetConfig') webWorksheetConfig: WebWorksheetConfigDataItem;

  pageSizeOptions: number[] = [25, 50, 100];

  // Observables
  worksheetStatus$: Observable<WorksheetStatusDataItem>;

  // Derived variables
  pageSize: number = 25;
  currentPageNumber: number = 1;
  pageNumbers: Array<number>;

  // Unsubscribe tracker
  private unsubscribe: Subscription[] = [];

  constructor(private store: Store<WorksheetState>) {
  }

  ngOnInit(): void {
    this.worksheetStatus$ = this.store.pipe(select(worksheetStatus));

    this.unsubscribe.push(
      this.worksheetStatus$.subscribe(worksheetStatus => {
        this.pageSize = worksheetStatus.pageSize;
        this.currentPageNumber = worksheetStatus.currentPageNumber;
        this.pageNumbers = Array.from({length: worksheetStatus.totalPages}, (_, i) => i + 1);
      })
    );
  }

  ngOnDestroy() {
    this.unsubscribe.forEach((sb) => sb.unsubscribe());
  }

  pageSizeChanged() {
    this.pageSize = Number(this.pageSize);
    this.store.dispatch(new PaginationPageSizeChange({
      pageSize: this.pageSize,
    }));
  }

  pageNumberChanged() {
    this.currentPageNumber = Number(this.currentPageNumber);
    this.pageNumberDispatch();
  }

  nextPage() {
    this.currentPageNumber++;
    this.pageNumberDispatch();
  }

  previousPage() {
    this.currentPageNumber--;
    this.pageNumberDispatch();
  }

  pageNumberDispatch() {
    this.store.dispatch(new PaginationPageNumberChange({
      currentPageNumber: this.currentPageNumber,
    }));
  }
}
