import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {HierarchyListItem} from "../../../../wsObjects/hierarchyListItem";

@Component({
  selector: 'list-item',
  templateUrl: './list-item.component.html',
  styleUrls: ['./list-item.component.scss'],
})
export class ListItemComponent implements OnInit, OnDestroy {
  @Input('hierarchyListItem') hierarchyListItems: Array<HierarchyListItem>;

  // Observables

  // Unsubscribe tracker
  private unsubscribe: Subscription[] = [];

  constructor() {

  }

  ngOnInit(): void {

  }

  ngOnDestroy() {
    this.unsubscribe.forEach((sb) => sb.unsubscribe());
  }
}
