import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable, of} from 'rxjs';
import {WebSocketService} from '../../../services/websocket.service';
import {Login} from "../../../wsActions/login";
import {AuthModel} from "../../../../modules/auth/models/auth.model";
import {environment} from "../../../../../environments/environment";
import {Router} from "@angular/router";
import {UserType} from "./auth.service";
import {finalize, map} from "rxjs/operators";
import {UserModel} from "../../../../modules/auth";

@Injectable()
export class AuthService {
  constructor(private webSocketService: WebSocketService, private router: Router) {
    this.currentUserSubject = new BehaviorSubject<UserType>(undefined);
  }

  private authLocalStorageToken = `${environment.appVersion}-${environment.USERDATA_KEY}`;

  currentUserSubject: BehaviorSubject<UserType>;

  get currentUserValue(): UserType {
    return this.currentUserSubject.value;
  }

  set currentUserValue(user: UserType) {
    this.currentUserSubject.next(user);
  }

  login(email: string, password: string): Observable<Login> {
    const loginAction: Login = new Login();
    loginAction.username = email;
    loginAction.password = password;

    return this.webSocketService.sendWithObservable(loginAction) as Observable<Login>;
  }

  // private methods
  setAuthFromLocalStorage(auth: AuthModel): boolean {
    // store auth authToken/refreshToken/epiresIn in local storage to keep user logged in between page refreshes
    if (auth && auth.authToken) {
      console.log("Auth token has been set to local");
      localStorage.setItem(this.authLocalStorageToken, JSON.stringify(auth));
      return true;
    }
    return false;
  }

  makeUserByToken(): Observable<UserModel | undefined> {
    const user = new UserModel();

    if (!user) {
      return of(undefined);
    }

    return of(user);
  }

  getUserByToken(): Observable<UserType> {
    const auth = this.getAuthFromLocalStorage();
    if (!auth || !auth.authToken) {
      console.log("returning fast");
      return of(undefined);
    }

    // this.isLoadingSubject.next(true);
    // Load from db basically
    return this.makeUserByToken().pipe(
      map((user: UserType) => {
        if (user) {
          this.currentUserSubject.next(user);
        } else {
          this.logout();
        }
        return user;
      }),
      // finalize(() => this.isLoadingSubject.next(false))
    );
  }

  logout() {
    localStorage.removeItem(this.authLocalStorageToken);
    this.router.navigate(['/auth/login'], {
      queryParams: {},
    });
  }

  getAuthFromLocalStorage(): AuthModel | undefined {
    try {
      const lsValue = localStorage.getItem(this.authLocalStorageToken);
      if (!lsValue) {
        return undefined;
      }

      const authData = JSON.parse(lsValue);
      return authData;
    } catch (error) {
      console.error(error);
      return undefined;
    }
  }
}
