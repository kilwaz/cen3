import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {select, Store} from "@ngrx/store";
import {HierarchyState} from "../../reducers/hierarchy.reducers";
import {HierarchyListDataItem} from "../../../../wsObjects/hierarchyListDataItem";
import {ClickedHierarchy, ExpandCollapseHierarchy, WorksheetLinkClicked} from "../../actions/hierarchy.actions";
import {isSelectedItem} from "../../selectors/hierarchy.selectors";

@Component({
  selector: 'list-item-content',
  templateUrl: './list-item-content.component.html',
  styleUrls: ['./list-item-content.component.scss'],
})
export class ListItemContentComponent implements OnInit, OnDestroy {
  @Input('hierarchyListItem') hierarchyListItem: HierarchyListDataItem;

  isSelected: boolean;

  // Observables

  // Unsubscribe tracker
  private unsubscribe: Subscription[] = [];

  constructor(private store: Store<HierarchyState>) {
  }

  ngOnInit(): void {
    this.unsubscribe.push(
      this.store.pipe(select(isSelectedItem(this.hierarchyListItem))).subscribe(result => {
        this.isSelected = result;
      })
    );
  }

  ngOnDestroy() {
    this.unsubscribe.forEach((sb) => sb.unsubscribe());
  }

  clicked(): void {
    this.store.dispatch(new ClickedHierarchy({
      selectedItem: this.hierarchyListItem
    }));
  }

  worksheetClicked(): void {
    this.store.dispatch(new WorksheetLinkClicked({
      worksheetId: this.hierarchyListItem.nodeReference,
      worksheetName: this.hierarchyListItem.title.replace(/ /g, '-')
    }));
  }

  expand(): void {
    this.store.dispatch(new ExpandCollapseHierarchy({
      update: {
        id: this.hierarchyListItem.nodeReference,
        changes: {expanded: true}
      }
    }));
  }

  collapse(): void {
    this.store.dispatch(new ExpandCollapseHierarchy({
      update: {
        id: this.hierarchyListItem.nodeReference,
        changes: {expanded: false}
      }
    }));
  }
}
