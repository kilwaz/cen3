import {Component, OnDestroy, OnInit} from '@angular/core';

// RxJS
import {Observable, Subject} from 'rxjs';

// Store
import {select, Store} from '@ngrx/store';
import {SummaryState} from './reducers/summary.reducers';

// Selectors
import {textToProcess} from './selectors/summary.selectors';
import {SummaryService} from './service/summary.service';

// Action

@Component({
  selector: 'app-summary',
  templateUrl: './summary.component.html',
  styleUrls: ['./summary.component.scss']
})
export class SummaryComponent implements OnInit, OnDestroy {
  textToProcess$: Observable<string>;

  private unsubscribe: Subject<any>;

  constructor(
    private hierarchyService: SummaryService,
    private store: Store<SummaryState>) {

    this.unsubscribe = new Subject();
  }

  ngOnInit(): void {
    this.textToProcess$ = this.store.pipe(select(textToProcess));
  }

  ngOnDestroy(): void {
    this.unsubscribe.complete();
  }
}
