import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {WebWorksheetConfigDataItem} from "../../../../wsObjects/webWorksheetConfigDataItem";

@Component({
  selector: '[worksheet-header]',
  templateUrl: './worksheet-header.component.html',
  styleUrls: ['./worksheet-header.component.scss'],
})
export class WorksheetHeaderComponent implements OnInit, OnDestroy {
  @Input('worksheetConfigs') worksheetConfigs: Array<WebWorksheetConfigDataItem>;

  // private fields
  private unsubscribe: Subscription[] = [];

  constructor() {
  }

  ngOnInit(): void {

  }

  ngOnDestroy() {
    this.unsubscribe.forEach((sb) => sb.unsubscribe());
  }
}
