import {Component, OnDestroy, OnInit} from '@angular/core';
import {Observable, Subscription} from 'rxjs';
import {select, Store} from "@ngrx/store";
import {configurableUi, content} from "../../selectors/summary.selectors";
import {SummaryState} from "../../reducers/summary.reducers";
import {LoadSummary} from "../../actions/summary.actions";
import {ConfigurableUiDataItem} from "../../../../wsObjects/configurableUiDataItem";


@Component({
  selector: 'worksheet-summary',
  templateUrl: './worksheet-summary.component.html',
  styleUrls: ['./worksheet-summary.component.scss'],
})
export class WorksheetSummaryComponent implements OnInit, OnDestroy {
  content$: Observable<string>;
  configurableUi$: Observable<Array<ConfigurableUiDataItem>>;

  private unsubscribe: Subscription[] = [];

  constructor(private store: Store<SummaryState>) {
  }

  ngOnInit(): void {
    this.store.dispatch(new LoadSummary({}));

    this.content$ = this.store.pipe(select(content));
    this.configurableUi$ = this.store.pipe(select(configurableUi));
  }

  ngOnDestroy() {
    this.unsubscribe.forEach((sb) => sb.unsubscribe());
  }

  protected readonly configurableUi = configurableUi;
}
