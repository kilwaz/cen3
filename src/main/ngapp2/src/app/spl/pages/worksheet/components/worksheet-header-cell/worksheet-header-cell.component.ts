import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {WebWorksheetConfig} from "../../../../wsObjects/webWorksheetConfig";
import {SetSortFilterColumn, SetSortFilterPopupPosition, ToggleSortFilterPopup} from "../../actions/worksheet.actions";
import {select, Store} from "@ngrx/store";
import {WorksheetState} from "../../reducers/worksheet.reducers";
import {isActiveSort} from "../../selectors/worksheet.selectors";

@Component({
  selector: '[worksheet-header-cell]',
  templateUrl: './worksheet-header-cell.component.html',
  styleUrls: ['./worksheet-header-cell.component.scss'],
})
export class WorksheetHeaderCellComponent implements OnInit, OnDestroy {
  @Input('webWorksheetConfig') webWorksheetConfig: WebWorksheetConfig;

  // Derived variables
  isActiveSort: boolean;

  // Unsubscribe tracker
  private unsubscribe: Subscription[] = [];

  constructor(private store: Store<WorksheetState>) {
  }

  ngOnInit(): void {
    this.unsubscribe.push(
      this.store.pipe(select(isActiveSort(this.webWorksheetConfig.definitionName))).subscribe(result => {
        this.isActiveSort = (result !== undefined);
      })
    );
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

  ngOnDestroy() {
    this.unsubscribe.forEach((sb) => sb.unsubscribe());
  }
}
