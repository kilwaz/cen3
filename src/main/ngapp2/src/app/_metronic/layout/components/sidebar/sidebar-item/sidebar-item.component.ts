import {Component, OnInit, Input} from '@angular/core';
import {environment} from '../../../../../../environments/environment';
import {Observable} from "rxjs";
import {select, Store} from "@ngrx/store";
import {username} from "../selectors/sidebar.selectors";
import {SideBarState} from "../reducers/sidebar.reducers";
import {MenuItem} from "../../../../../spl/wsObjects/menuItem";

@Component({
  selector: 'app-sidebar-item',
  templateUrl: './sidebar-item.component.html',
  styleUrls: ['./sidebar-item.component.scss'],
})
export class SidebarItemComponent implements OnInit {
  appAngularVersion: string = environment.appVersion;
  appPreviewChangelogUrl: string = environment.appPreviewChangelogUrl;

  // routerLink: string = "/dashboard123";
  // @Input('title$') title$: Observable<string>;
  @Input('menuItem') menuItem: MenuItem;
  // icon: string = "./assets/media/icons/duotune/art/art002.svg";

  // title: string = "Default";
  //
  // thing$: Observable<string>;

  constructor(
    // private store: Store<SideBarState>
  ) {
  }

  ngOnInit(): void {
    // this.thing$ = this.store.pipe(select(username));
  }
}
