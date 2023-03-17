// Angular
import {Injectable} from '@angular/core';
// RxJS
import {map, tap} from 'rxjs/operators';
// NGRX
import {Actions, concatLatestFrom, createEffect, ofType} from '@ngrx/effects';
import {select, Store} from '@ngrx/store';
// Actions
import {
  TextCasesActionTypes, ProcessText, TextResultUpdated
} from '../actions/text-cases.actions';
import {AppState} from '../../../core/reducers';
import {TextCasesService} from '../service/text-cases.service';
import {selectTextCases} from '../selectors/text-cases.selectors';

@Injectable()
export class TextCasesEffects {
  constructor(private actions$: Actions,
              private textCaseService: TextCasesService, private store: Store<AppState>) {
  }

  processText$ = createEffect(() => {
    return this.actions$.pipe(
      ofType<ProcessText>(TextCasesActionTypes.ProcessText),
      concatLatestFrom(() =>
        this.store.pipe(select(selectTextCases))
      ),
      map(([, textProcess]) => {
        this.textCaseService.textFunction(textProcess.textToProcess, textProcess.textFunction)
          .pipe(
            tap(result => {
              this.store.dispatch(new TextResultUpdated({
                textResult: result.textResult
              }));
            }),
          ).subscribe(); // Does this subscribe forever?
      }));
  }, {dispatch: false});
}
