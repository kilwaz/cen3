import {Component, OnDestroy, OnInit} from '@angular/core';

// RxJS
import {Observable, Subject} from 'rxjs';

// Store
import {select, Store} from '@ngrx/store';
import {ManagementState} from './reducers/management.reducers';

// Selectors
import {freeMemory, maxMemory, totalMemory} from './selectors/management.selectors';
import {ManagementService} from './service/management.service';
import {QueryManagement} from "./actions/management.actions";

// Action

@Component({
  selector: 'management',
  templateUrl: './management.component.html',
  styleUrls: ['./management.component.scss']
})
export class ManagementComponent implements OnInit, OnDestroy {
  totalMemory$: Observable<number>;
  freeMemory$: Observable<number>;
  maxMemory$: Observable<number>;

  private unsubscribe: Subject<any>;

  constructor(
    private hierarchyService: ManagementService,
    private store: Store<ManagementState>) {

    this.unsubscribe = new Subject();
  }

  ngOnInit(): void {
    this.totalMemory$ = this.store.pipe(select(totalMemory));
    this.freeMemory$ = this.store.pipe(select(freeMemory));
    this.maxMemory$ = this.store.pipe(select(maxMemory));

    this.store.dispatch(new QueryManagement({}));
  }

  ngOnDestroy(): void {
    this.unsubscribe.complete();
  }
}
