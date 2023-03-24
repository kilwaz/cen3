import { Injectable } from '@angular/core';
import {
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
} from '@angular/router';
import { AuthService } from './auth2.service';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
  constructor(private authService: AuthService) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    console.log("Auth guard is being checked..");
    const auth = this.authService.getAuthFromLocalStorage();
    if (auth) {
      // logged in so return true
      console.log("Returning true from guard");
      return true;
    }

    // not logged in so redirect to login page with the return url
    this.authService.logout();
    console.log("Returning false from guard");
    return false;
  }
}
