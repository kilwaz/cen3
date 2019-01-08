import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthedService {

  private validated: boolean = true;

  constructor() {
  }

  getValidated() {
    return this.validated;
  }
}
