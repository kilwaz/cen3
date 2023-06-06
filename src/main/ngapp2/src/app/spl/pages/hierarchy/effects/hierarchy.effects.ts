// Angular
import {Injectable} from '@angular/core';
// RxJS
import {map, tap} from 'rxjs/operators';
// NGRX
import {Actions, concatLatestFrom, createEffect, ofType} from '@ngrx/effects';
import {select, Store} from '@ngrx/store';
// Actions
import {HierarchyActionTypes, LoadHierarchy, RequestHierarchy} from '../actions/hierarchy.actions';
import {HierarchyService} from '../service/hierarchy.service';
import {selectHierarchy} from '../selectors/hierarchy.selectors';
import {HierarchyState} from "../reducers/hierarchy.reducers";

@Injectable()
export class HierarchyEffects {
  constructor(private actions$: Actions,
              private hierarchyService: HierarchyService, private store: Store<HierarchyState>) {
  }

  requestHierarchy$ = createEffect(() => {
    return this.actions$.pipe(
      ofType<RequestHierarchy>(HierarchyActionTypes.RequestHierarchy),
      concatLatestFrom(() =>
        this.store.pipe(select(selectHierarchy))
      ),
      map(result => {
        this.hierarchyService.requestHierarchy()
          .pipe(
            tap(result => {
              this.store.dispatch(new LoadHierarchy({
                hierarchyItems: result.hierarchyItems
              }));
            }),
          ).subscribe();
      })
    );
  }, {dispatch: false});
}
