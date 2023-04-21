import {Component, OnInit, OnDestroy, Input} from '@angular/core';
import {Subscription} from 'rxjs';
import {MenuItem} from "../../../../wsObjects/menuItem";
import {WebRecord} from "../../../../wsObjects/webRecord";


@Component({
  selector: 'worksheet-table',
  templateUrl: './worksheet-table.component.html',
  styleUrls: ['./worksheet-table.component.scss'],
})
export class WorksheetTableComponent implements OnInit, OnDestroy {
  @Input('worksheetRecords') worksheetRecords: Array<WebRecord>;

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
