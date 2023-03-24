// Actions
import {AuthActions, AuthActionTypes} from "../actions/auth.actions";

export interface LoginResultState {
  username: string;
  password: string;
  acceptedAuth: boolean;
  errorMessage: string;
}

export const initialAuthState: LoginResultState = {
  username: "",
  password: "",
  acceptedAuth: false,
  errorMessage: ""
};

export function authReducer(state = initialAuthState, action: AuthActions): LoginResultState {
  switch (action.type) {
    case AuthActionTypes.LoginAttemptResult: {
      const acceptedAuthPayload: boolean = action.payload.acceptedAuth;
      const errorMessagePayload: string = action.payload.errorMessage;

      return {
        ...state,
        acceptedAuth: acceptedAuthPayload,
        errorMessage: errorMessagePayload
      };
    }
    case AuthActionTypes.InputUsernameUpdated: {
      const usernamePayload: string = action.payload.username;

      return {
        ...state,
        username: usernamePayload
      };
    }
    case AuthActionTypes.InputPasswordUpdated: {
      const passwordPayload: string = action.payload.password;

      return {
        ...state,
        password: passwordPayload
      };
    }
    default:
      return state;
  }
}
