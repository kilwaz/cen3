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
  AddSortItem,
  ApplyUpdate,
  ClearSort,
  ProcessFilteredListData,
  ProcessWorksheetData,
  RemoveSortItem,
  RequestWorksheetData,
  ToggleSortFilterPopup,
  Update,
  UpdateSortFilter,
  WorksheetActionTypes
} from "../actions/worksheet.actions";
import {selectWorksheet, sortFilter} from "../selectors/worksheet.selectors";
import {map, tap} from "rxjs/operators";
import {SortFilterService} from "../service/sort-filter.service";
import {SortFilter} from "../../../wsObjects/sortFilter";
import {UpdateService} from "../service/update.service";

@Injectable()
export class WorksheetEffects {
  constructor(private actions$: Actions,
              private store: Store<AppState>,
              private worksheetService: WorksheetService,
              private sortFilterService: SortFilterService,
              private updateService: UpdateService) {
  }

  requestWorksheetData$ = createEffect(() => {
    return this.actions$.pipe(
      ofType<RequestWorksheetData>(WorksheetActionTypes.RequestWorksheetData),
      concatLatestFrom(() =>
        this.store.pipe(select(selectWorksheet))
      ),
      map(([, worksheetState]) => {
        this.worksheetService.worksheetRequest(worksheetState.sortFilter).pipe(
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

  addSortItem$ = createEffect(() => {
    return this.actions$.pipe(
      ofType<AddSortItem>(WorksheetActionTypes.AddSortItem),
      concatLatestFrom(() =>
        this.store.pipe(select(sortFilter))
      ),
      tap(([addSortItem, sortFilter]) => {
        this.sortFilterService.addSort(addSortItem.payload.sort, sortFilter);

        this.store.dispatch(new RequestWorksheetData({
          requestID: '10174'
        }));
      })
    );
  }, {dispatch: false});

  removeSortItem$ = createEffect(() => {
    return this.actions$.pipe(
      ofType<RemoveSortItem>(WorksheetActionTypes.RemoveSortItem),
      concatLatestFrom(() =>
        this.store.pipe(select(sortFilter))
      ),
      tap(([removeSortItem, sortFilter]) => {
        this.sortFilterService.removeSort(removeSortItem.payload.sortReference, sortFilter);

        this.store.dispatch(new RequestWorksheetData({
          requestID: '10174'
        }));
      })
    );
  }, {dispatch: false});

  clearSort$ = createEffect(() => {
    return this.actions$.pipe(
      ofType<ClearSort>(WorksheetActionTypes.ClearSort),
      concatLatestFrom(() =>
        this.store.pipe(select(sortFilter))
      ),
      tap(() => {
        this.store.dispatch(new UpdateSortFilter({
          sortFilter: new SortFilter()
        }));
      })
    );
  }, {dispatch: false});

  update$ = createEffect(() => {
    return this.actions$.pipe(
      ofType<Update>(WorksheetActionTypes.Update),
      concatLatestFrom(() =>
        this.store.pipe(select(sortFilter))
      ),
      tap(([update]) => {
        let payload = update.payload;
        this.updateService.update(payload.value, payload.definitionName, payload.recordUUID, payload.updateSource).pipe(
          tap(result => {
            this.store.dispatch(new ApplyUpdate({
              webRecord: result.webRecord,
              recordUUID: result.recordUUID
            }));
          }),
        ).subscribe();
      })
    );
  }, {dispatch: false});
}
