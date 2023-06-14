import {Component, OnInit, OnDestroy, Input} from '@angular/core';
import {Subscription} from 'rxjs';
import {WebRecord} from "../../../../wsObjects/webRecord";
import {WebWorksheetConfig} from "../../../../wsObjects/webWorksheetConfig";


@Component({
  selector: '[worksheet-row]',
  templateUrl: './worksheet-row.component.html',
  styleUrls: ['./worksheet-row.component.scss'],
})
export class WorksheetRowComponent implements OnInit, OnDestroy {
  @Input('worksheetRowRecord') worksheetRowRecord: WebRecord;
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
