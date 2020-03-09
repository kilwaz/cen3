import {Component, OnInit} from '@angular/core';
import {testAction} from "../action/test.action";
import {Store} from "@ngrx/store";
import {Observable} from "rxjs";

@Component({
  selector: 'app-ngrxtest',
  templateUrl: './ngrxtest.component.html',
  styleUrls: ['./ngrxtest.component.css']
})
export class NgrxtestComponent implements OnInit {
  test$: Observable<string>;

  constructor(private store: Store<any>) {
    this.test$ = this.store.select('aReducer', 'testString');
  };

  ngOnInit(): void {
  }

  clickMeh(str: string): void {
    this.store.dispatch(testAction({testString: str}));
  }
}
