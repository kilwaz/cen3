// Angular
import {Injectable} from '@angular/core';
// RxJS
import {map, tap} from 'rxjs/operators';
// NGRX
import {Actions, concatLatestFrom, createEffect, ofType} from '@ngrx/effects';
import {select, Store} from '@ngrx/store';
// Actions
import {
  DownloadTestRequest,
  ManagementActionTypes,
  ProcessDownloadResults,
  ProcessResults,
  QueryManagement
} from '../actions/management.actions';
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

  downloadTestRequest$ = createEffect(() => {
    return this.actions$.pipe(
      ofType<DownloadTestRequest>(ManagementActionTypes.DownloadTestRequest),
      map(() => {
        this.managementService.downloadTest()
          .pipe(
            tap(result => {
              let data = atob(result.content);
              let dataArray = new Uint8Array(data.length);

              for (let i = 0; i < data.length; i++) {
                dataArray[i] = data.charCodeAt(i);
              }

              let blob = new Blob([dataArray]);

              this.store.dispatch(new ProcessDownloadResults({
                fileData: blob
              }));
            }),
          ).subscribe(); // Does this subscribe forever?
      }));
  }, {dispatch: false});
}
