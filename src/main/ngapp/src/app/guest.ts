import {APIGuest} from "./model/aPIGuest";

export class Guest implements APIGuest {

  constructor() {
  }

  civilAccepted: boolean;
  email: string;
  firstName: string;
  lastName: string;
  mehndiAccepted: boolean;
  receptionAccepted: boolean;
}
