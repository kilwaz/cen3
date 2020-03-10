import {Component, OnInit} from '@angular/core';
import {loadRecordAction, ngrxtestAction} from "../action/ngrxtest.action";
import {Store} from "@ngrx/store";
import {Observable} from "rxjs";
import {Record} from "../wsObjects/record";
import {WebSocketService} from "../services/websocket.service";

@Component({
  selector: 'app-ngrxtest',
  templateUrl: './ngrxtest.component.html',
  styleUrls: ['./ngrxtest.component.css']
})
export class NgrxtestComponent implements OnInit {
  test$: Observable<string> = this.store.select('aReducer', 'testString');
  //records$: Observable<Record[]> = this.store.select(state => {records: state.records});

  private webSocketServiceConst: WebSocketService;

  constructor(private store: Store<any>) {
  };

  ngOnInit(): void {
    this.store.dispatch(loadRecordAction());
  }

  clickMeh(str: string): void {
    this.store.dispatch(ngrxtestAction({testString: str}));
  }
}
