import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Observable, Subscription} from 'rxjs';
import {HierarchyListItem} from "../../../../wsObjects/hierarchyListItem";
import {selectYourEntityByIds} from "../../selectors/hierarchy.selectors";
import {Store} from "@ngrx/store";
import {HierarchyState} from "../../reducers/hierarchy.reducers";

@Component({
  selector: 'list-item',
  templateUrl: './list-item.component.html',
  styleUrls: ['./list-item.component.scss'],
})
export class ListItemComponent implements OnInit, OnDestroy {
  // @Input('hierarchyListItem') hierarchyListItems: Array<HierarchyListItem>;
  @Input('hierarchyListItem') hierarchyListItem: HierarchyListItem;

  // Observables
  children$: Observable<Array<HierarchyListItem>>;

  // Unsubscribe tracker
  private unsubscribe: Subscription[] = [];

  constructor(private store: Store<HierarchyState>) {

  }

  ngOnInit(): void {
    this.children$ = this.store.select(selectYourEntityByIds(this.hierarchyListItem.childrenIds));
  }

  ngOnDestroy() {
    this.unsubscribe.forEach((sb) => sb.unsubscribe());
  }
}
