import {Component, OnInit, OnDestroy} from '@angular/core';
import {Subscription} from 'rxjs';


@Component({
  selector: 'worksheet-header',
  templateUrl: './worksheet-header.component.html',
  styleUrls: ['./worksheet-header.component.scss'],
})
export class WorksheetHeaderComponent implements OnInit, OnDestroy {
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
