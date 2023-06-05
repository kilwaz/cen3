// Angular
import {Injectable} from '@angular/core';
// RxJS
import {map, tap} from 'rxjs/operators';
// NGRX
import {Actions, concatLatestFrom, createEffect, ofType} from '@ngrx/effects';
import {select, Store} from '@ngrx/store';
// Actions
import {DashboardActionTypes, ProcessText} from '../actions/dashboard.actions';
import {AppState} from '../../../core/reducers';
import {DashboardService} from '../service/dashboard.service';
import {selectTextCases} from '../selectors/dashboard.selectors';

@Injectable()
export class DashboardEffects {
  constructor(private actions$: Actions,
              private dashboardService: DashboardService, private store: Store<AppState>) {
  }

  processText$ = createEffect(() => {
    return this.actions$.pipe(
      ofType<ProcessText>(DashboardActionTypes.ProcessText),
      concatLatestFrom(() =>
        this.store.pipe(select(selectTextCases))
      ),
      map(([, textProcess]) => {
        // this.dashboardService.textFunction(textProcess.textToProcess, textProcess.textFunction)
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
