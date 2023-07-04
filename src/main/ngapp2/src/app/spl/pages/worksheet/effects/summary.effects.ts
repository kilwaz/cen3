// Angular
import {Injectable} from '@angular/core';
// RxJS
// NGRX
import {Actions, concatLatestFrom, createEffect, ofType} from '@ngrx/effects';
import {select, Store} from '@ngrx/store';
// Actions
import {selectWorksheet} from "../selectors/worksheet.selectors";
import {map, tap} from "rxjs/operators";
import {SummaryService} from "../service/summary.service";
import {SummaryState} from "../reducers/summary.reducers";
import {LoadSummary, SummaryActionTypes, UpdateSummary} from "../actions/summary.actions";

@Injectable()
export class SummaryEffects {
  constructor(private actions$: Actions,
              private store: Store<SummaryState>,
              private summaryService: SummaryService) {
  }

  loadSummary$ = createEffect(() => {
    return this.actions$.pipe(
      ofType<LoadSummary>(SummaryActionTypes.LoadSummary),
      concatLatestFrom(() =>
        this.store.pipe(select(selectWorksheet))
      ),
      map(([, worksheetState]) => {
        this.summaryService.loadSummary(worksheetState.requestID).pipe(
          tap(result => {
            this.store.dispatch(new UpdateSummary({
              content: result.content,
            }));
          }),
        ).subscribe(); // Does this subscribe forever?
      }));
  }, {dispatch: false});
}