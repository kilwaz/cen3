import {Component, OnInit} from '@angular/core';
import {LoadRecordAction, NgrxtestAction} from "../action/ngrxtest.action";
import {createSelector, select, Store} from "@ngrx/store";
import {Observable} from "rxjs";
import {RecordContainer} from "../containers/recordContainer";

@Component({
  selector: 'app-ngrxtest',
  templateUrl: './ngrxtest.component.html',
  styleUrls: ['./ngrxtest.component.css']
})

export class NgrxtestComponent implements OnInit {
  test$: Observable<string> = this.store.select((state: any) => state.search.testString);
  records$: Observable<RecordContainer[]> = this.store.select((state: any) => state.search.records);

  selectTestString = createSelector(
    (state: any) => state.search.testString,
    testString => testString + " Added"
  );

  test2$: Observable<string> = this.store.pipe(select(this.selectTestString));
  test3$: Observable<string> = this.store.select((state: any) => state.search.testString);

  constructor(private store: Store<any>) {
  };

  ngOnInit(): void {
    this.store.dispatch(LoadRecordAction());
  }

  clickMeh(str: string): void {
    this.store.dispatch(NgrxtestAction({testString: str}));
  }
}
