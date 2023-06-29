import {Component, OnDestroy, OnInit} from '@angular/core';

// RxJS
import {Observable, Subscription} from 'rxjs';

// Store
import {select, Store} from '@ngrx/store';
import {WorksheetState} from './reducers/worksheet.reducers';

// Selectors
import {WorksheetService} from './service/worksheet.service';
import {requestID, worksheetConfigs, worksheetRecords} from "./selectors/worksheet.selectors";
import {ClearSort, ProcessWorksheetData, RequestWorksheetData} from "./actions/worksheet.actions";
import {WebRecord} from "../../wsObjects/webRecord";
import {WebWorksheetConfig} from "../../wsObjects/webWorksheetConfig";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-worksheet',
  templateUrl: './worksheet.component.html',
  styleUrls: ['./worksheet.component.scss']
})
export class WorksheetComponent implements OnInit, OnDestroy {
  requestID$: Observable<string>;
  worksheetRecords$: Observable<Array<WebRecord>>;
  worksheetConfigs$: Observable<Array<WebWorksheetConfig>>;

  worksheetID: string;

  private unsubscribe: Subscription[] = [];

  constructor(
    private worksheetService: WorksheetService,
    private store: Store<WorksheetState>,
    private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.requestID$ = this.store.pipe(select(requestID));
    this.worksheetRecords$ = this.store.pipe(select(worksheetRecords));
    this.worksheetConfigs$ = this.store.pipe(select(worksheetConfigs));

    this.unsubscribe.push(
      this.route.paramMap.subscribe(params => {
        this.worksheetID = params.get('worksheetId');
      })
    );

    this.store.dispatch(new RequestWorksheetData({
      requestID: this.worksheetID
    }));
  }

  ngOnDestroy() {
    this.unsubscribe.forEach((sb) => sb.unsubscribe());
  }
}
