// Angular
import {Injectable} from '@angular/core';
// RxJS
import {map, tap} from 'rxjs/operators';
// NGRX
import {Actions, concatLatestFrom, createEffect, ofType} from '@ngrx/effects';
import {select, Store} from '@ngrx/store';
// Actions
import {ManagementActionTypes, ProcessResults, QueryManagement} from '../actions/management.actions';
import {ManagementService} from '../service/management.service';
import {ManagementState} from "../reducers/management.reducers";
import {selectManagement} from "../selectors/management.selectors";

@Injectable()
export class ManagementEffects {
  constructor(private actions$: Actions,
              private managementService: ManagementService, private store: Store<ManagementState>) {
  }

  queryManagement$ = createEffect(() => {
    return this.actions$.pipe(
      ofType<QueryManagement>(ManagementActionTypes.QueryManagement),
      concatLatestFrom(() =>
        this.store.pipe(select(selectManagement))
      ),
      map(([, management]) => {
        this.managementService.queryManagement()
          .pipe(
            tap(result => {
              this.store.dispatch(new ProcessResults({
                totalMemory: result.totalMemory,
                freeMemory: result.freeMemory,
                maxMemory: result.maxMemory
              }));
            }),
          ).subscribe(); // Does this subscribe forever?
      }));
  }, {dispatch: false});
}
