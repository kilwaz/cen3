import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {WebWorksheetConfig} from "../../../../wsObjects/webWorksheetConfig";
import {SetSortFilterColumn, SetSortFilterPopupPosition, ToggleSortFilterPopup} from "../../actions/worksheet.actions";
import {Store} from "@ngrx/store";
import {WorksheetState} from "../../reducers/worksheet.reducers";

@Component({
  selector: '[worksheet-header-cell]',
  templateUrl: './worksheet-header-cell.component.html',
  styleUrls: ['./worksheet-header-cell.component.scss'],
})
export class WorksheetHeaderCellComponent implements OnInit, OnDestroy {
  @Input('webWorksheetConfig') webWorksheetConfig: WebWorksheetConfig;

  // private fields
  private unsubscribe: Subscription[] = []; // Read more: => https://brianflove.com/2016/12/11/anguar-2-unsubscribe-observables/

  constructor(private store: Store<WorksheetState>) {
  }

  ngOnInit(): void {
    this.webWorksheetConfig.name;
  }

  ngOnDestroy() {
    // this.unsubscribe.forEach((sb) => sb.unsubscribe());
  }

  openPopup(e: MouseEvent) {
    let element: Element = e.target as Element;
    let rect = element.getBoundingClientRect();

    this.store.dispatch(new SetSortFilterColumn({
      currentSortFilterColumn: this.webWorksheetConfig
    }));
    this.store.dispatch(new SetSortFilterPopupPosition({
      sortFilterPopupX: rect.x,
      sortFilterPopupY: rect.y + rect.height
    }));
    this.store.dispatch(new ToggleSortFilterPopup({
      isOpen: true
    }));
  }
}
