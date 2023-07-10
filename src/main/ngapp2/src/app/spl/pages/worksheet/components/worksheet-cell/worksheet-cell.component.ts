import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {WebEntryDataItem} from "../../../../wsObjects/webEntryDataItem";
import {WebWorksheetConfigDataItem} from "../../../../wsObjects/webWorksheetConfigDataItem";
import {Store} from "@ngrx/store";
import {WorksheetState} from "../../reducers/worksheet.reducers";
import {Update} from "../../actions/worksheet.actions";


@Component({
  selector: '[worksheet-cell]',
  templateUrl: './worksheet-cell.component.html',
  styleUrls: ['./worksheet-cell.component.scss'],
})
export class WorksheetCellComponent implements OnInit, OnDestroy {
  @Input('worksheetCellEntry') worksheetCellEntry: WebEntryDataItem;
  @Input('worksheetConfig') worksheetConfig: WebWorksheetConfigDataItem;

  updatedValue: boolean;
  value: string = "";

  // private fields
  private unsubscribe: Subscription[] = [];

  constructor(private store: Store<WorksheetState>) {
  }

  ngOnInit(): void {
    this.value = this.worksheetCellEntry.value;
  }

  ngOnDestroy() {
    this.unsubscribe.forEach((sb) => sb.unsubscribe());
  }

  valueChange(): void {
    this.updatedValue = true;
  }

  focusOut(): void {
    if (this.value !== this.worksheetCellEntry.value) {
      this.store.dispatch(new Update({
        value: this.value,
        definitionName: this.worksheetCellEntry.name,
        recordUUID: this.worksheetCellEntry.recordUUID,
        updateSource: 'worksheet'
      }));
    }
  }
}
