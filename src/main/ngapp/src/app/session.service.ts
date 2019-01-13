import {Injectable} from '@angular/core';
import {ValidatedRSVP} from "./validatedRSVP";

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  private _validatedRSVP: ValidatedRSVP;

  constructor() {
  }

  get validatedRSVP(): ValidatedRSVP {
    return this._validatedRSVP;
  }

  set validatedRSVP(value: ValidatedRSVP) {
    this._validatedRSVP = value;
  }
}
