import {Component, OnDestroy, OnInit} from '@angular/core';

// RxJS
import {Observable, Subscription} from 'rxjs';

// Store
import {select, Store} from '@ngrx/store';
import {ManagementState} from './reducers/management.reducers';

// Selectors
import {freeMemory, maxMemory, totalMemory} from './selectors/management.selectors';
import {ManagementService} from './service/management.service';
import {
  DownloadConfigRequest,
  DownloadTestRequest,
  QueryManagement,
  RecalculateHierarchy
} from "./actions/management.actions";

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

  private unsubscribe: Subscription[] = [];

  constructor(
    private hierarchyService: ManagementService,
    private store: Store<ManagementState>) {
  }

  ngOnInit(): void {
    this.totalMemory$ = this.store.pipe(select(totalMemory));
    this.freeMemory$ = this.store.pipe(select(freeMemory));
    this.maxMemory$ = this.store.pipe(select(maxMemory));

    this.store.dispatch(new QueryManagement({}));
  }

  ngOnDestroy() {
    this.unsubscribe.forEach((sb) => sb.unsubscribe());
  }

  triggerDownload(): void {
    this.store.dispatch(new DownloadTestRequest({}));
  }

  triggerConfigDownload(): void {
    this.store.dispatch(new DownloadConfigRequest({}));
  }

  recalculateHierarchy(): void {
    this.store.dispatch(new RecalculateHierarchy({}));
  }
}
