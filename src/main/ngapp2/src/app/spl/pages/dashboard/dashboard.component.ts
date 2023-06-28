import {Component, OnDestroy, OnInit} from '@angular/core';

// RxJS
import {Observable, Subscription} from 'rxjs';

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

  private unsubscribe: Subscription[] = [];

  constructor(
    private hierarchyService: DashboardService,
    private store: Store<DashboardState>) {
  }

  ngOnInit(): void {
    this.textToProcess$ = this.store.pipe(select(textToProcess));
  }

  ngOnDestroy() {
    this.unsubscribe.forEach((sb) => sb.unsubscribe());
  }
}
