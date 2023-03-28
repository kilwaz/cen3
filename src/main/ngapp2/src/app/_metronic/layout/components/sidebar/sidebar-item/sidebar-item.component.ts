import {Component, OnInit, Input} from '@angular/core';
import {environment} from '../../../../../../environments/environment';

@Component({
  selector: 'app-sidebar-item',
  templateUrl: './sidebar-item.component.html',
  styleUrls: ['./sidebar-item.component.scss'],
})
export class SidebarItemComponent implements OnInit {
  appAngularVersion: string = environment.appVersion;
  appPreviewChangelogUrl: string = environment.appPreviewChangelogUrl;

  routerLink: string = "/dashboard";
  @Input('title') title: string = "Default?";
  icon: string = "./assets/media/icons/duotune/art/art002.svg";

  constructor() {
  }

  ngOnInit(): void {

  }
}
