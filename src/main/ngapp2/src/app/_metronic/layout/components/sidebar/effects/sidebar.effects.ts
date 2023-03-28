// Angular
import {Injectable} from '@angular/core';
// RxJS
import {map, tap} from 'rxjs/operators';
// NGRX
import {Actions, concatLatestFrom, createEffect, ofType} from '@ngrx/effects';
import {select, Store} from '@ngrx/store';
// Actions
import {
  LoadRoles, RequestMenuLayout, SideBarActionTypes
} from '../actions/sidebar.actions';
import {AppState} from '../../../../../spl/core/reducers';
import {SideBarService} from "../service/sidebar.service";
import {selectRoles} from "../selectors/sidebar.selectors";

@Injectable()
export class SideBarEffects {
  constructor(private actions$: Actions,
              private sideBarService: SideBarService, private store: Store<AppState>) {
  }

  requestMenuLayout$ = createEffect(() => {
    return this.actions$.pipe(
      ofType<RequestMenuLayout>(SideBarActionTypes.RequestMenuLayout),
      concatLatestFrom(() =>
        this.store.pipe(select(selectRoles))
      ),
      map(result => {
        this.sideBarService.requestMenuLayout()
          .pipe(
            tap(result => {
              this.store.dispatch(new LoadRoles({
                username: result.username,
                menuItems: result.menuItems
              }));
            }),
          ).subscribe();
      })
    );
  }, {dispatch: false});
}
