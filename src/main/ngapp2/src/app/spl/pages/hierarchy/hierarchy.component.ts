import {Component, OnDestroy, OnInit} from '@angular/core';

// RxJS
import {Observable, Subject} from 'rxjs';

// Store
import {select, Store} from '@ngrx/store';
import {HierarchyItemsState, HierarchyState} from './reducers/hierarchy.reducers';

// Selectors
import {hierarchyItems, selectAll, selectYourEntityById, selectYourEntityByIds} from './selectors/hierarchy.selectors';
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
  hierarchyItems2$: Observable<HierarchyItemsState>;

  rootNode$: Observable<HierarchyListItem>;
  rootNodes$: Observable<Array<HierarchyListItem>>;

  private unsubscribe: Subject<any>;

  constructor(
    private hierarchyService: HierarchyService,
    private store: Store<HierarchyState>) {

    this.unsubscribe = new Subject();
  }

  ngOnInit(): void {
    this.store.dispatch(new RequestHierarchy({}));

    this.rootNode$ = this.store.select(selectYourEntityById('ARUP'));

    this.rootNodes$ = this.store.select(selectYourEntityByIds(['ARUP']));

    this.hierarchyItems2$ = this.store.pipe(select(hierarchyItems));
    this.hierarchyItems$ = this.hierarchyItems2$.pipe(select(selectAll));
  }

  ngOnDestroy(): void {
    this.unsubscribe.complete();
  }
}
