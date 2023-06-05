import {Component, OnDestroy, OnInit} from '@angular/core';

// RxJS
import {Observable, Subject} from 'rxjs';

// Store
import {select, Store} from '@ngrx/store';
import {HierarchyState} from './reducers/hierarchy.reducers';

// Selectors
import {textToProcess} from './selectors/hierarchy.selectors';
import {HierarchyService} from './service/hierarchy.service';

// Action

@Component({
  selector: 'app-hierarchy',
  templateUrl: './hierarchy.component.html',
  styleUrls: ['./hierarchy.component.scss']
})
export class HierarchyComponent implements OnInit, OnDestroy {
  textToProcess$: Observable<string>;

  private unsubscribe: Subject<any>;

  constructor(
    private hierarchyService: HierarchyService,
    private store: Store<HierarchyState>) {

    this.unsubscribe = new Subject();
  }

  ngOnInit(): void {
    this.textToProcess$ = this.store.pipe(select(textToProcess));
  }

  ngOnDestroy(): void {
    this.unsubscribe.complete();
  }
}
