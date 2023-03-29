import {Component, OnInit, Input} from '@angular/core';
import {MenuItem} from "../../../../../spl/wsObjects/menuItem";

@Component({
  selector: 'app-sidebar-item',
  templateUrl: './sidebar-item.component.html',
  styleUrls: ['./sidebar-item.component.scss'],
})
export class SidebarItemComponent implements OnInit {
  @Input('menuItem') menuItem: MenuItem;

  constructor() {
  }

  ngOnInit(): void {
  }
}
