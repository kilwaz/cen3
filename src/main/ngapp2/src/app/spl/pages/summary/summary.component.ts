import {Component, OnDestroy, OnInit} from '@angular/core';

// RxJS
import {Observable, Subscription} from 'rxjs';

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

  private unsubscribe: Subscription[] = [];

  constructor(
    private hierarchyService: SummaryService,
    private store: Store<SummaryState>) {
  }

  ngOnInit(): void {
    this.textToProcess$ = this.store.pipe(select(textToProcess));
  }

  ngOnDestroy() {
    this.unsubscribe.forEach((sb) => sb.unsubscribe());
  }
}
