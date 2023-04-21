// Angular
import {Injectable} from '@angular/core';
// RxJS
// NGRX
import {Actions, concatLatestFrom, createEffect, ofType} from '@ngrx/effects';
import {select, Store} from '@ngrx/store';
// Actions
import {AppState} from '../../../core/reducers';
import {WorksheetService} from '../service/worksheet.service';
import {ProcessWorksheetData, RequestWorksheetData, WorksheetActionTypes} from "../actions/worksheet.actions";
import {selectWorksheet} from "../selectors/worksheet.selectors";
import {map, tap} from "rxjs/operators";

@Injectable()
export class WorksheetEffects {
  constructor(private actions$: Actions,
              private Worksheetervice: WorksheetService, private store: Store<AppState>) {
  }

  requestWorksheetData$ = createEffect(() => {
    return this.actions$.pipe(
      ofType<RequestWorksheetData>(WorksheetActionTypes.RequestWorksheetData),
      concatLatestFrom(() =>
        this.store.pipe(select(selectWorksheet))
      ),
      map(([, uploadFile]) => {
        this.Worksheetervice.testFunction().pipe(
          tap(result => {
            this.store.dispatch(new ProcessWorksheetData({
              requestID: result.requestID,
              worksheetRecords: result.worksheetRecords
            }));
          }),
        ).subscribe(); // Does this subscribe forever?
      }));
  }, {dispatch: false});

  // processText$ = createEffect(() => {
  //   return this.actions$.pipe(
  //     ofType<ProcessText>(WorksheetActionTypes.ProcessText),
  //     concatLatestFrom(() =>
  //       this.store.pipe(select(selectWorksheet))
  //     ),
  //     map(([, textProcess]) => {
  //       this.Worksheetervice.textFunction(textProcess.textToProcess, textProcess.textFunction)
  //         .pipe(
  //           tap(result => {
  //             this.store.dispatch(new TextResultUpdated({
  //               textResult: result.textResult
  //             }));
  //           }),
  //         ).subscribe(); // Does this subscribe forever?
  //     }));
  // }, {dispatch: false});
  //
  // filePicked$ = createEffect(() => {
  //   return this.actions$.pipe(
  //     ofType<FilePicked>(WorksheetActionTypes.FilePicked),
  //     tap(x => {
  //       this.store.dispatch(new UploadFileBegin({}));
  //     })
  //   );
  // }, {dispatch: false});

  // uploadFile$ = createEffect(() => {
  //   return this.actions$.pipe(
  //     ofType<UploadFileBegin>(WorksheetActionTypes.UploadFileBegin),
  //     concatLatestFrom(() =>
  //       this.store.pipe(select(selectWorksheet))
  //     ),
  //     map(([, uploadFile]) => {
  //       this.Worksheetervice.sendTestFile(uploadFile.fileToUpload);
  //       // .pipe(
  //       //   tap(result => {
  //       //
  //       //   }),
  //       // ).subscribe(); // Does this subscribe forever?
  //     }));
  // }, {dispatch: false});
}
