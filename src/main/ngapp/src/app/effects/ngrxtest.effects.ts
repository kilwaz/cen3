import {Injectable} from '@angular/core';
import {Actions, createEffect, ofType} from '@ngrx/effects';
import {EMPTY, Observable, of} from 'rxjs';
import {catchError, map, mergeMap} from 'rxjs/operators';
import {WebSocketService} from "../services/websocket.service";
import {loadRecordAction} from "../action/ngrxtest.action";
import {Record} from "../wsObjects/record";
import {RecordContainer} from "../containers/recordContainer";
import {Search} from "../wsActions/search";

@Injectable()
export class NgrxtestEffects {
  searchResults: Array<RecordContainer> = new Array<RecordContainer>();

  loadRecords$ = createEffect(() => this.actions$.pipe(
    ofType(loadRecordAction),
    mergeMap(() => this.load()
      .pipe(
        map(records => ({type: '[WebSocket API] Records Loaded Success', payload: records})),
        catchError(() => EMPTY)
      ))
    )
  );

  load(): Observable<Record[]> {
    let search: Search = new Search();
    search.searchItem = "Sum";
    search.searchValue = "10";
    let _this: NgrxtestEffects = this;

    this.webSocketService.sendCallback(search, function (responseMessage) {
      let searchResponse: Search = <Search>responseMessage;

      _this.searchResults = [];
      searchResponse.searchResults.forEach(function (record) {
        _this.searchResults.push(new RecordContainer(record));
      });
    });

    return of();
  }

  constructor(private actions$: Actions, private webSocketService: WebSocketService) {
  }
}
