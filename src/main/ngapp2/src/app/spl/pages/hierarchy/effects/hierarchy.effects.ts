// Angular
import {Injectable} from '@angular/core';
// RxJS
import {map, tap} from 'rxjs/operators';
// NGRX
import {Actions, concatLatestFrom, createEffect, ofType} from '@ngrx/effects';
import {select, Store} from '@ngrx/store';
// Actions
import {
  HierarchyActionTypes,
  LoadHierarchy,
  RequestHierarchy,
  WorksheetLinkClicked
} from '../actions/hierarchy.actions';
import {HierarchyService} from '../service/hierarchy.service';
import {selectHierarchy} from '../selectors/hierarchy.selectors';
import {HierarchyState} from "../reducers/hierarchy.reducers";
import {Router} from "@angular/router";
import {UpdateWorksheetLink} from "../../../../_metronic/layout/components/sidebar/actions/sidebar.actions";

@Injectable()
export class HierarchyEffects {
  constructor(private actions$: Actions,
              private hierarchyService: HierarchyService,
              private store: Store<HierarchyState>,
              private router: Router) {
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

  worksheetLinkClicked$ = createEffect(() => {
    return this.actions$.pipe(
      ofType<WorksheetLinkClicked>(HierarchyActionTypes.WorksheetLinkClicked),
      concatLatestFrom(() =>
        this.store.pipe(select(selectHierarchy))
      ),
      map(([worksheetLinkClicked]) => {
        let worksheetLink = '/worksheet/' + worksheetLinkClicked.payload.worksheetId + '/' + worksheetLinkClicked.payload.worksheetName;
        this.router.navigate([worksheetLink]);
        this.store.dispatch(new UpdateWorksheetLink({
          update: {
            id: "Worksheet",
            changes: {routeLink: worksheetLink}
          }
        }));
      }),
    );
  }, {dispatch: false});

}
