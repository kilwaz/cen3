import {Component, OnInit, OnDestroy, Input} from '@angular/core';
import {Subscription} from 'rxjs';
import {WebEntry} from "../../../../wsObjects/webEntry";


@Component({
  selector: 'worksheet-cell',
  templateUrl: './worksheet-cell.component.html',
  styleUrls: ['./worksheet-cell.component.scss'],
})
export class WorksheetCellComponent implements OnInit, OnDestroy {
  @Input('worksheetCellEntry') worksheetCellEntry: WebEntry;

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
