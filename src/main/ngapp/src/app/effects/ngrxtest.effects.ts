import {Injectable} from '@angular/core';
import {Actions, createEffect, ofType} from '@ngrx/effects';
import {EMPTY, Observable, of, Subject, Subscription} from 'rxjs';
import {catchError, map, mergeMap} from 'rxjs/operators';
import {WebSocketService} from "../services/websocket.service";
import {loadRecordAction} from "../action/ngrxtest.action";
import {RecordContainer} from "../containers/recordContainer";
import {Search} from "../wsActions/search";

@Injectable()
export class NgrxtestEffects {
  loadRecords$ = createEffect(() => this.actions$.pipe(
    ofType(loadRecordAction),
    mergeMap(() => this.load()
      .pipe(
        map(records => ({type: '[WebSocket API] Records Loaded Success', payload: records})),
        catchError(() => EMPTY)
      ))
    )
  );

  load(): Observable<RecordContainer[]> {
    let search: Search = new Search();
    search.searchItem = "Sum";
    search.searchValue = "10";
    let searchResults: Array<RecordContainer> = new Array<RecordContainer>();

    let obsThing: Observable<Array<RecordContainer>> = of(searchResults);
    let subject = new Subject();
    let subThing: Subscription = subject.subscribe({
      next: (result: RecordContainer[]) => {
        console.log(result);
      },
      error: (err: any) => {
        console.log("Error " + err);
      }
    });

    this.webSocketService.sendCallback(search, function (responseMessage) {
      let searchResponse: Search = <Search>responseMessage;

      searchResults = new Array<RecordContainer>();
      searchResponse.searchResults.forEach(function (record) {
        subject.next(new RecordContainer(record));
        searchResults.push(new RecordContainer(record));
      });
    });

    return obsThing;
  }

  constructor(private actions$: Actions, private webSocketService: WebSocketService) {
  }
}
