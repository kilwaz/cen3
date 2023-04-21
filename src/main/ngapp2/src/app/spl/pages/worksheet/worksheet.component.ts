import {Component, OnDestroy, OnInit} from '@angular/core';

// RxJS
import {Observable, Subject} from 'rxjs';

// Store
import {select, Store} from '@ngrx/store';
import {WorksheetState} from './reducers/worksheet.reducers';

// Selectors
import {WorksheetService} from './service/worksheet.service';
import {requestID, worksheetRecords} from "./selectors/worksheet.selectors";
import {RequestWorksheetData} from "./actions/worksheet.actions";
import {WebRecord} from "../../wsObjects/webRecord";

@Component({
  selector: 'app-worksheet',
  templateUrl: './worksheet.component.html',
  styleUrls: ['./worksheet.component.scss']
})
export class WorksheetComponent implements OnInit, OnDestroy {
  requestID$: Observable<string>;
  worksheetRecords$: Observable<Array<WebRecord>>;

  private unsubscribe: Subject<any>;

  constructor(
    private worksheetService: WorksheetService,
    private store: Store<WorksheetState>) {

    this.unsubscribe = new Subject();
  }

  ngOnInit(): void {
    this.requestID$ = this.store.pipe(select(requestID));
    this.worksheetRecords$ = this.store.pipe(select(worksheetRecords));

    this.store.dispatch(new RequestWorksheetData({
      requestID: '10174'
    }));
  }

  ngOnDestroy(): void {
    this.unsubscribe.complete();
  }
}
