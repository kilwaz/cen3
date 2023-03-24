// Angular
import {Injectable} from '@angular/core';
// RxJS
import {finalize, first, map, tap} from 'rxjs/operators';
// NGRX
import {Actions, concatLatestFrom, createEffect, ofType} from '@ngrx/effects';
import {select, Store} from '@ngrx/store';
// Actions
import {
  AuthActionTypes,
  LoginAttemptResult, SubmitLogin
} from '../actions/auth.actions';
import {AppState} from '../../../core/reducers';
import {selectAuth} from '../selectors/auth.selectors';
import {AuthService} from "../services/auth2.service";
import {Router} from "@angular/router";
import {AuthModel} from "../../../../modules/auth/models/auth.model";

@Injectable()
export class AuthEffects {
  constructor(private actions$: Actions, private authService: AuthService, private store: Store<AppState>, private router: Router) {

  }

  loginAttemptResult$ = createEffect(() => {
    return this.actions$.pipe(
      ofType<LoginAttemptResult>(AuthActionTypes.LoginAttemptResult),
      concatLatestFrom(() =>
        this.store.pipe(select(selectAuth))
      ),
      map(([, loginResult]) => {
        if (loginResult.acceptedAuth) {
          const auth = new AuthModel();
          auth.authToken = "authToken";
          auth.refreshToken = "refresh.token";
          auth.expiresIn = new Date(Date.now() + 100 * 24 * 60 * 60 * 1000);
          this.authService.setAuthFromLocalStorage(auth);
          // this.authService.getUserByToken();
        }
      }),
      tap(() => {
        this.router.navigate(['']);
      })
    )
  }, {dispatch: false});

  submitLogin$ = createEffect(() => {
    return this.actions$.pipe(
      ofType<SubmitLogin>(AuthActionTypes.SubmitLogin),
      concatLatestFrom(() =>
        this.store.pipe(select(selectAuth))
      ),
      map(([, loginResult]) => {
        this.authService.login(loginResult.username, loginResult.password)
          .pipe(
            tap(result => {
              this.store.dispatch(new LoginAttemptResult({
                acceptedAuth: result.acceptedAuth,
                errorMessage: result.errorMessage
              }));
            }),
          ).subscribe(); // Does this subscribe forever?
      }));
  }, {dispatch: false});
}
