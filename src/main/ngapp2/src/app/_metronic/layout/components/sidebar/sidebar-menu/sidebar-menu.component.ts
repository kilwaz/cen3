import { Component, OnInit } from '@angular/core';
import {SideBarService} from "../service/sidebar.service";
import {select, Store} from "@ngrx/store";
import {SideBarState} from "../reducers/sidebar.reducers";
import {menuItems} from "../selectors/sidebar.selectors";
import {Observable} from "rxjs";
import {MenuItem} from "../../../../../spl/wsObjects/menuItem";

@Component({
  selector: 'app-sidebar-menu',
  templateUrl: './sidebar-menu.component.html',
  styleUrls: ['./sidebar-menu.component.scss']
})
export class SidebarMenuComponent implements OnInit {
  menuItems$: Observable<MenuItem[]>;

  constructor(private sideBarService: SideBarService, private store: Store<SideBarState>) { }

  ngOnInit(): void {
    this.menuItems$ = this.store.pipe(select(menuItems));
  }
}
