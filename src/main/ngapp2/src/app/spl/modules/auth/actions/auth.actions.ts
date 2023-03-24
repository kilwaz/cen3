import {Action} from '@ngrx/store';

export enum AuthActionTypes {
  LoginAttemptResult = '[Auth-LoginAttemptResult] Action',
  InputUsernameUpdated = '[Auth-InputUsernameUpdated] Action',
  InputPasswordUpdated = '[Auth-InputPasswordUpdated] Action',
  SubmitLogin = '[Auth-SubmitLogin] Action'
}

export class LoginAttemptResult implements Action {
  readonly type = AuthActionTypes.LoginAttemptResult;

  constructor(public payload: {
    acceptedAuth: boolean,
    errorMessage: string
  }) {
  }
}

export class InputUsernameUpdated implements Action {
  readonly type = AuthActionTypes.InputUsernameUpdated;

  constructor(public payload: {
    username: string,
  }) {
  }
}

export class InputPasswordUpdated implements Action {
  readonly type = AuthActionTypes.InputPasswordUpdated;

  constructor(public payload: {
    password: string,
  }) {
  }
}

export class SubmitLogin implements Action {
  readonly type = AuthActionTypes.SubmitLogin;

  constructor(public payload: {
  }) {
  }
}

export type AuthActions =
  LoginAttemptResult |
  InputUsernameUpdated |
  InputPasswordUpdated |
  SubmitLogin;
