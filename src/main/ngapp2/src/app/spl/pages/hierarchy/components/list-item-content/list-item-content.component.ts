import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {select, Store} from "@ngrx/store";
import {HierarchyState} from "../../reducers/hierarchy.reducers";
import {HierarchyListItem} from "../../../../wsObjects/hierarchyListItem";
import {ClickedHierarchy} from "../../actions/hierarchy.actions";
import {isSelectedItem} from "../../selectors/hierarchy.selectors";
import {Router} from "@angular/router";

@Component({
  selector: 'list-item-content',
  templateUrl: './list-item-content.component.html',
  styleUrls: ['./list-item-content.component.scss'],
})
export class ListItemContentComponent implements OnInit, OnDestroy {
  @Input('hierarchyListItem') hierarchyListItem: HierarchyListItem;

  isSelected: boolean;

  // Observables

  // Unsubscribe tracker
  private unsubscribe: Subscription[] = [];

  constructor(private store: Store<HierarchyState>,
              private router: Router) {
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
    this.router.navigate(['/worksheet/' + this.hierarchyListItem.nodeReference + '/' + this.hierarchyListItem.title.replace(/ /g, '-')]);
  }
}
