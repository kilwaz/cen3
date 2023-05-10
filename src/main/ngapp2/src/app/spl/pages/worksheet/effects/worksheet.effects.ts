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
  WorksheetActionTypes, ProcessFilteredListData
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
      map(() => {
        this.worksheetService.worksheetRequest().pipe(
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

  toggleSortFilterPopup$ = createEffect(() => {
    return this.actions$.pipe(
      ofType<ToggleSortFilterPopup>(WorksheetActionTypes.ToggleSortFilterPopup),
      concatLatestFrom(() =>
        this.store.pipe(select(selectWorksheet))
      ),
      tap(([, worksheetState]) => {
        if (worksheetState.isSortFilterOpen) {
          this.worksheetService.filteredListRequest(worksheetState.currentSortFilterColumn.definitionName).pipe(
            tap(result => {
              this.store.dispatch(new ProcessFilteredListData({
                filteredList: result.listItem,
              }));
            }),
          ).subscribe();
        }
      })
    );
  }, {dispatch: false});
}
