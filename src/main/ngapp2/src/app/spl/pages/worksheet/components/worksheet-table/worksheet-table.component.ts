import {Component, OnInit, OnDestroy, Input} from '@angular/core';
import {Subscription} from 'rxjs';
import {WebRecord} from "../../../../wsObjects/webRecord";
import {WebWorksheetConfig} from "../../../../wsObjects/webWorksheetConfig";
import {ToggleSortFilterPopup} from "../../actions/worksheet.actions";
import {Store} from "@ngrx/store";
import {WorksheetState} from "../../reducers/worksheet.reducers";


@Component({
  selector: 'worksheet-table',
  templateUrl: './worksheet-table.component.html',
  styleUrls: ['./worksheet-table.component.scss'],
})
export class WorksheetTableComponent implements OnInit, OnDestroy {
  @Input('worksheetRecords') worksheetRecords: Array<WebRecord>;
  @Input('worksheetConfigs') worksheetConfigs: Array<WebWorksheetConfig>;

  // private fields
  private unsubscribe: Subscription[] = []; // Read more: => https://brianflove.com/2016/12/11/anguar-2-unsubscribe-observables/

  constructor(private store: Store<WorksheetState>) {
  }

  ngOnInit(): void {

  }

  ngOnDestroy() {
    // this.unsubscribe.forEach((sb) => sb.unsubscribe());
  }

  closeSortFilter(){
    this.store.dispatch(new ToggleSortFilterPopup({
      isOpen: false
    }));
  }
}
