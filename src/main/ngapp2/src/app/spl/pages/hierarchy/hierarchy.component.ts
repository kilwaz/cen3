import {Component, OnDestroy, OnInit} from '@angular/core';

// RxJS
import {Observable, Subject} from 'rxjs';

// Store
import {select, Store} from '@ngrx/store';
import {HierarchyState} from './reducers/hierarchy.reducers';

// Selectors
import {hierarchyItems} from './selectors/hierarchy.selectors';
import {HierarchyService} from './service/hierarchy.service';
import {HierarchyListItem} from "../../wsObjects/hierarchyListItem";
import {RequestHierarchy} from "./actions/hierarchy.actions";

// Action

@Component({
  selector: 'app-hierarchy',
  templateUrl: './hierarchy.component.html',
  styleUrls: ['./hierarchy.component.scss']
})
export class HierarchyComponent implements OnInit, OnDestroy {
  hierarchyItems$: Observable<Array<HierarchyListItem>>;

  private unsubscribe: Subject<any>;

  constructor(
    private hierarchyService: HierarchyService,
    private store: Store<HierarchyState>) {

    this.unsubscribe = new Subject();
  }

  ngOnInit(): void {
    this.store.dispatch(new RequestHierarchy({}));

    this.hierarchyItems$ = this.store.pipe(select(hierarchyItems));
  }

  ngOnDestroy(): void {
    this.unsubscribe.complete();
  }
}
