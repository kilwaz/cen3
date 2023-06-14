import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {WebEntry} from "../../../../wsObjects/webEntry";
import {WebWorksheetConfig} from "../../../../wsObjects/webWorksheetConfig";
import {Store} from "@ngrx/store";
import {WorksheetState} from "../../reducers/worksheet.reducers";
import {Update} from "../../actions/worksheet.actions";


@Component({
  selector: '[worksheet-cell]',
  templateUrl: './worksheet-cell.component.html',
  styleUrls: ['./worksheet-cell.component.scss'],
})
export class WorksheetCellComponent implements OnInit, OnDestroy {
  @Input('worksheetCellEntry') worksheetCellEntry: WebEntry;
  @Input('worksheetConfig') worksheetConfig: WebWorksheetConfig;

  updatedValue: boolean;
  value: string = "";

  // private fields
  private unsubscribe: Subscription[] = []; // Read more: => https://brianflove.com/2016/12/11/anguar-2-unsubscribe-observables/

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
