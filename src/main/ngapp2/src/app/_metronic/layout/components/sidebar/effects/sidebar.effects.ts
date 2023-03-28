// Angular
import {Injectable} from '@angular/core';
// RxJS
import {map, tap} from 'rxjs/operators';
// NGRX
import {Actions, concatLatestFrom, createEffect, ofType} from '@ngrx/effects';
import {select, Store} from '@ngrx/store';
// Actions
import {
  LoadRolesAction, SideBarActionTypes
} from '../actions/sidebar.actions';
import {AppState} from '../../../../../spl/core/reducers';
import {SideBarService} from "../service/sidebar.service";
import {selectRoles} from "../selectors/sidebar.selectors";

@Injectable()
export class TextCasesEffects {
  constructor(private actions$: Actions,
              private sideBarService: SideBarService, private store: Store<AppState>) {
  }

  loadRole$ = createEffect(() => {
    return this.actions$.pipe(
      ofType<LoadRolesAction>(SideBarActionTypes.LoadRoles),
      concatLatestFrom(() =>
        this.store.pipe(select(selectRoles))
      ),
      map(([, loadRole]) => {
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
