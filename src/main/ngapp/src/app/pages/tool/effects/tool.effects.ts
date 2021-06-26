// Angular
import {Injectable} from '@angular/core';
import {NavigationEnd, Router} from '@angular/router';
// RxJS
import {filter, mergeMap, tap, withLatestFrom} from 'rxjs/operators';
// NGRX
import {Actions, createEffect, Effect, ofType} from '@ngrx/effects';
import {Action, select, Store} from '@ngrx/store';
// Actions
import {
  Test, ToolActionTypes
} from '../actions/tool.actions';
import {AppState} from '../../../core/reducers';

@Injectable()
export class ToolEffects {
  test$ = createEffect(() => {
    return this.actions$.pipe(
      ofType<Test>(ToolActionTypes.Test),
      tap(action => {
        // this.router.navigate(['/dashboard'], {queryParams: {returnUrl: this.returnUrl}});
      }));
  });

  private returnUrl: string;

  constructor(private actions$: Actions,
              private router: Router,
              private store: Store<AppState>) {

    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.returnUrl = event.url;
      }
    });
  }
}
