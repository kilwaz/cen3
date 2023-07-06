import {Component, OnDestroy, OnInit} from '@angular/core';
import {Observable, Subscription} from 'rxjs';
import {select, Store} from "@ngrx/store";
import {content} from "../../selectors/summary.selectors";
import {SummaryState} from "../../reducers/summary.reducers";
import {LoadSummary} from "../../actions/summary.actions";


@Component({
  selector: 'worksheet-summary',
  templateUrl: './worksheet-summary.component.html',
  styleUrls: ['./worksheet-summary.component.scss'],
})
export class WorksheetSummaryComponent implements OnInit, OnDestroy {
  content$: Observable<string>;

  private unsubscribe: Subscription[] = [];

  constructor(private store: Store<SummaryState>) {
  }

  ngOnInit(): void {
    this.store.dispatch(new LoadSummary({}));

    this.content$ = this.store.pipe(select(content));
  }

  ngOnDestroy() {
    this.unsubscribe.forEach((sb) => sb.unsubscribe());
  }
}
