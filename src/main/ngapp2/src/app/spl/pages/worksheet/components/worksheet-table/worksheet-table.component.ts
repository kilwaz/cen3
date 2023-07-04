import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {WebRecordDataItem} from "../../../../wsObjects/webRecordDataItem";
import {WebWorksheetConfigDataItem} from "../../../../wsObjects/webWorksheetConfigDataItem";
import {ToggleSortFilterPopup} from "../../actions/worksheet.actions";
import {Store} from "@ngrx/store";
import {WorksheetState} from "../../reducers/worksheet.reducers";

@Component({
  selector: 'worksheet-table',
  templateUrl: './worksheet-table.component.html',
  styleUrls: ['./worksheet-table.component.scss'],
})
export class WorksheetTableComponent implements OnInit, OnDestroy {
  @Input('worksheetRecords') worksheetRecords: Array<WebRecordDataItem>;
  @Input('worksheetConfigs') worksheetConfigs: Array<WebWorksheetConfigDataItem>;

  // private fields
  private unsubscribe: Subscription[] = [];

  constructor(private store: Store<WorksheetState>) {
  }

  ngOnInit(): void {

  }

  ngOnDestroy() {
    this.unsubscribe.forEach((sb) => sb.unsubscribe());
  }

  closeSortFilter() {
    this.store.dispatch(new ToggleSortFilterPopup({
      isOpen: false
    }));
  }
}
