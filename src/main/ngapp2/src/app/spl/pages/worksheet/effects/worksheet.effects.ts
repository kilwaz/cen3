// Angular
import {Injectable} from '@angular/core';
// RxJS
// NGRX
import {Actions, concatLatestFrom, createEffect, ofType} from '@ngrx/effects';
import {select, Store} from '@ngrx/store';
// Actions
import {AppState} from '../../../core/reducers';
import {WorksheetService} from '../service/worksheet.service';
import {
  ToggleSortFilterPopup,
  ProcessWorksheetData,
  RequestWorksheetData,
  WorksheetActionTypes
} from "../actions/worksheet.actions";
import {selectWorksheet} from "../selectors/worksheet.selectors";
import {map, tap} from "rxjs/operators";

@Injectable()
export class WorksheetEffects {
  constructor(private actions$: Actions,
              private worksheetService: WorksheetService, private store: Store<AppState>) {
  }

  requestWorksheetData$ = createEffect(() => {
    return this.actions$.pipe(
      ofType<RequestWorksheetData>(WorksheetActionTypes.RequestWorksheetData),
      concatLatestFrom(() =>
        this.store.pipe(select(selectWorksheet))
      ),
      map(([, uploadFile]) => {
        this.worksheetService.testFunction().pipe(
          tap(result => {
            this.store.dispatch(new ProcessWorksheetData({
              requestID: result.requestID,
              worksheetRecords: result.worksheetRecords,
              worksheetConfigs: result.worksheetConfig
            }));
          }),
        ).subscribe(); // Does this subscribe forever?
      }));
  }, {dispatch: false});
}
