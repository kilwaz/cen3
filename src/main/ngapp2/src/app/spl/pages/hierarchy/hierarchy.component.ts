import {Component, OnDestroy, OnInit} from '@angular/core';

// RxJS
import {Observable, Subscription} from 'rxjs';

// Store
import {select, Store} from '@ngrx/store';
import {HierarchyItemsState, HierarchyState} from './reducers/hierarchy.reducers';

// Selectors
import {hierarchyItems, reloadHierarchy, selectAll, selectYourEntityByIds} from './selectors/hierarchy.selectors';
import {HierarchyService} from './service/hierarchy.service';
import {HierarchyListDataItem} from "../../wsObjects/hierarchyListDataItem";
import {RequestHierarchy} from "./actions/hierarchy.actions";

// Action

@Component({
  selector: 'app-hierarchy',
  templateUrl: './hierarchy.component.html',
  styleUrls: ['./hierarchy.component.scss']
})
export class HierarchyComponent implements OnInit, OnDestroy {
  hierarchyItems$: Observable<Array<HierarchyListDataItem>>;
  hierarchyItemsState$: Observable<HierarchyItemsState>;

  reloadHierarchy$: Observable<boolean>;

  rootNodes$: Observable<Array<HierarchyListDataItem>>;

  private unsubscribe: Subscription[] = [];

  constructor(
    private hierarchyService: HierarchyService,
    private store: Store<HierarchyState>) {
  }

  ngOnInit(): void {
    this.reloadHierarchy$ = this.store.pipe(select(reloadHierarchy));

    this.unsubscribe.push(
      this.reloadHierarchy$.subscribe(reloadHierarchy => {
        if (reloadHierarchy) {
          this.store.dispatch(new RequestHierarchy({}));
        }
      })
    );

    this.rootNodes$ = this.store.select(selectYourEntityByIds(['ARUP']));

    this.hierarchyItemsState$ = this.store.pipe(select(hierarchyItems));
    this.hierarchyItems$ = this.hierarchyItemsState$.pipe(select(selectAll));
  }

  ngOnDestroy() {
    this.unsubscribe.forEach((sb) => sb.unsubscribe());
  }
}
