import {Component, OnDestroy, OnInit} from '@angular/core';

// RxJS
import {Observable, Subject} from 'rxjs';

// Store
import {select, Store} from '@ngrx/store';
import {WorksheetState} from './reducers/worksheet.reducers';

// Selectors
import {WorksheetService} from './service/worksheet.service';
import {requestID, worksheetConfigs, worksheetRecords} from "./selectors/worksheet.selectors";
import {ClearSort, ProcessWorksheetData, RequestWorksheetData, UpdateSortFilter} from "./actions/worksheet.actions";
import {WebRecord} from "../../wsObjects/webRecord";
import {WebWorksheetConfig} from "../../wsObjects/webWorksheetConfig";
import {SortFilter} from "../../wsObjects/sortFilter";

@Component({
  selector: 'app-worksheet',
  templateUrl: './worksheet.component.html',
  styleUrls: ['./worksheet.component.scss']
})
export class WorksheetComponent implements OnInit, OnDestroy {
  requestID$: Observable<string>;
  worksheetRecords$: Observable<Array<WebRecord>>;
  worksheetConfigs$: Observable<Array<WebWorksheetConfig>>;

  private unsubscribe: Subject<any>;

  constructor(
    private worksheetService: WorksheetService,
    private store: Store<WorksheetState>) {

    this.unsubscribe = new Subject();
  }

  ngOnInit(): void {
    this.requestID$ = this.store.pipe(select(requestID));
    this.worksheetRecords$ = this.store.pipe(select(worksheetRecords));
    this.worksheetConfigs$ = this.store.pipe(select(worksheetConfigs));

    this.store.dispatch(new RequestWorksheetData({
      requestID: '10174'
    }));
  }

  clearWorksheetData(): void {
    this.store.dispatch(new ProcessWorksheetData({
      requestID: null,
      worksheetRecords: null,
      worksheetConfigs: null
    }));
    this.store.dispatch(new ClearSort({}));
  }

  ngOnDestroy(): void {
    this.unsubscribe.complete();
  }
}
