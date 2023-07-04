import {Component, OnInit, Input} from '@angular/core';
import {MenuDataItem} from "../../../../../spl/wsObjects/menuDataItem";

@Component({
  selector: 'app-sidebar-item',
  templateUrl: './sidebar-item.component.html',
  styleUrls: ['./sidebar-item.component.scss'],
})
export class SidebarItemComponent implements OnInit {
  @Input('menuItem') menuItem: MenuDataItem;

  constructor() {
  }

  ngOnInit(): void {
  }
}
