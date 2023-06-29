import {Component, OnInit} from '@angular/core';
import {SideBarService} from "../service/sidebar.service";
import {select, Store} from "@ngrx/store";
import {MenuItemsState, MenuState} from "../reducers/sidebar.reducers";
import {menuItems, selectAll} from "../selectors/sidebar.selectors";
import {Observable} from "rxjs";
import {MenuItem} from "../../../../../spl/wsObjects/menuItem";

@Component({
  selector: 'app-sidebar-menu',
  templateUrl: './sidebar-menu.component.html',
  styleUrls: ['./sidebar-menu.component.scss']
})
export class SidebarMenuComponent implements OnInit {
  menuItems$: Observable<Array<MenuItem>>;
  menuItemsState$: Observable<MenuItemsState>;

  constructor(private sideBarService: SideBarService, private store: Store<MenuState>) {
  }

  ngOnInit(): void {
    this.menuItemsState$ = this.store.pipe(select(menuItems));
    this.menuItems$ = this.menuItemsState$.pipe(select(selectAll));
  }
}
