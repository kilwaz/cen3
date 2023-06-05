import {Component, OnDestroy, OnInit} from '@angular/core';

// RxJS
import {Observable, Subject} from 'rxjs';

// Store
import {select, Store} from '@ngrx/store';
import {DashboardState} from './reducers/dashboard.reducers';

// Selectors
import {textToProcess} from './selectors/dashboard.selectors';
import {DashboardService} from './service/dashboard.service';

// Action

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit, OnDestroy {
  textToProcess$: Observable<string>;

  private unsubscribe: Subject<any>;

  constructor(
    private hierarchyService: DashboardService,
    private store: Store<DashboardState>) {

    this.unsubscribe = new Subject();
  }

  ngOnInit(): void {
    this.textToProcess$ = this.store.pipe(select(textToProcess));
  }

  ngOnDestroy(): void {
    this.unsubscribe.complete();
  }
}
