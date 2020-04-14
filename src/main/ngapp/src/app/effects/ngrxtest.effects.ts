import {Injectable} from '@angular/core';
import {Actions, createEffect, Effect, ofType} from '@ngrx/effects';
import {Observable, of} from 'rxjs';
import {catchError, map, mergeMap} from 'rxjs/operators';
import {WebSocketService} from "../services/websocket.service";
import {LoadRecordAction} from "../action/ngrxtest.action";
import {RecordContainer} from "../containers/recordContainer";
import {Search} from "../wsActions/search";

@Injectable()
export class NgrxtestEffects {
  constructor(private actions$: Actions, private webSocketService: WebSocketService) {
  }

  @Effect()
  loadRecords$ = createEffect(() => this.actions$
    .pipe(
      ofType(LoadRecordAction),
      mergeMap(() => this.load()
        .pipe(
          map(records => ({type: '[WebSocket API] Records Loaded Success', payload: records})),
          catchError(() => of({type: '[WebSocket API] Records Loaded Failed'}))
        ))
    )
  );

  load(): Observable<RecordContainer[]> {
    let search: Search = new Search();
    search.searchItem = "Sum";
    search.searchValue = "10";
    let searchResults: Array<RecordContainer> = new Array<RecordContainer>();

    return new Observable<RecordContainer[]>(observer => {
      this.webSocketService.sendCallback(search, function (responseMessage) {
        let searchResponse: Search = <Search>responseMessage;
        searchResults = new Array<RecordContainer>();
        searchResponse.searchResults.forEach(function (record) {
          searchResults.push(new RecordContainer(record));
        });
        observer.next(searchResults);
      });
    });
  }
}
