import {Component, OnInit, OnDestroy, Input, ViewChild, ElementRef} from '@angular/core';
import {Subscription} from 'rxjs';
import {WebWorksheetConfig} from "../../../../wsObjects/webWorksheetConfig";

@Component({
  selector: '[worksheet-header]',
  templateUrl: './worksheet-header.component.html',
  styleUrls: ['./worksheet-header.component.scss'],
})
export class WorksheetHeaderComponent implements OnInit, OnDestroy {
  @Input('worksheetConfigs') worksheetConfigs: Array<WebWorksheetConfig>;

  // private fields
  private unsubscribe: Subscription[] = []; // Read more: => https://brianflove.com/2016/12/11/anguar-2-unsubscribe-observables/

  constructor() {
  }

  ngOnInit(): void {

  }

  ngOnDestroy() {
    // this.unsubscribe.forEach((sb) => sb.unsubscribe());
  }
}
