// Angular
import {Injectable} from '@angular/core';
// RxJS
import {map, tap} from 'rxjs/operators';
// NGRX
import {Actions, concatLatestFrom, createEffect, ofType} from '@ngrx/effects';
import {select, Store} from '@ngrx/store';
// Actions
import {HierarchyActionTypes, ProcessText} from '../actions/hierarchy.actions';
import {AppState} from '../../../core/reducers';
import {HierarchyService} from '../service/hierarchy.service';
import {selectTextCases} from '../selectors/hierarchy.selectors';

@Injectable()
export class HierarchyEffects {
  constructor(private actions$: Actions,
              private textCaseService: HierarchyService, private store: Store<AppState>) {
  }

  processText$ = createEffect(() => {
    return this.actions$.pipe(
      ofType<ProcessText>(HierarchyActionTypes.ProcessText),
      concatLatestFrom(() =>
        this.store.pipe(select(selectTextCases))
      ),
      map(([, textProcess]) => {
        this.textCaseService.textFunction(textProcess.textToProcess, textProcess.textFunction)
          .pipe(
            tap(result => {
              // this.store.dispatch(new TextResultUpdated({
              //   textResult: result.textResult
              // }));
            }),
          ).subscribe(); // Does this subscribe forever?
      }));
  }, {dispatch: false});
}
