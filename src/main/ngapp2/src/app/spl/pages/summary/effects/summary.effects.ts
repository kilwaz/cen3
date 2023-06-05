// Angular
import {Injectable} from '@angular/core';
// RxJS
import {map} from 'rxjs/operators';
// NGRX
import {Actions, concatLatestFrom, createEffect, ofType} from '@ngrx/effects';
import {select, Store} from '@ngrx/store';
// Actions
import {ProcessText, SummaryActionTypes} from '../actions/summary.actions';
import {AppState} from '../../../core/reducers';
import {SummaryService} from '../service/summary.service';
import {selectTextCases} from '../selectors/summary.selectors';

@Injectable()
export class SummaryEffects {
  constructor(private actions$: Actions,
              private textCaseService: SummaryService, private store: Store<AppState>) {
  }

  processText$ = createEffect(() => {
    return this.actions$.pipe(
      ofType<ProcessText>(SummaryActionTypes.ProcessText),
      concatLatestFrom(() =>
        this.store.pipe(select(selectTextCases))
      ),
      map(([, textProcess]) => {
        // this.textCaseService.textFunction(textProcess.textToProcess, textProcess.textFunction)
        //   .pipe(
        //     tap(result => {
        //       this.store.dispatch(new TextResultUpdated({
        //         textResult: result.textResult
        //       }));
        //     }),
        //   ).subscribe(); // Does this subscribe forever?
      }));
  }, {dispatch: false});
}
