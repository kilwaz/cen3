import {Component, OnInit, OnDestroy, Input} from '@angular/core';
import {Subscription} from 'rxjs';


@Component({
  selector: 'worksheet-summary',
  templateUrl: './worksheet-summary.component.html',
  styleUrls: ['./worksheet-summary.component.scss'],
})
export class WorksheetSummaryComponent implements OnInit, OnDestroy {
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
