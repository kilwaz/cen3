import {Component, OnInit} from '@angular/core';
import {loadRecordAction, ngrxtestAction} from "../action/ngrxtest.action";
import {Store} from "@ngrx/store";
import {AsyncSubject, Observable, Subject} from "rxjs";
import {RecordContainer} from "../containers/recordContainer";

@Component({
  selector: 'app-ngrxtest',
  templateUrl: './ngrxtest.component.html',
  styleUrls: ['./ngrxtest.component.css']
})
export class NgrxtestComponent implements OnInit {
  test$: Observable<string> = this.store.select('aReducer', 'testString');
  records$: Observable<RecordContainer[]> = this.store.select(state => state.records);

  constructor(private store: Store<any>) {
  };

  ngOnInit(): void {
    this.store.dispatch(loadRecordAction());
  }

  clickMeh(str: string): void {
    this.store.dispatch(ngrxtestAction({testString: str}));
  }
}
