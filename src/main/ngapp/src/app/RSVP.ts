import {Guest} from "./guest";
import {APIRSVP} from "./model/aPIRSVP";
import {APIGuest} from "./model/aPIGuest";

export class RSVP implements APIRSVP {
  constructor() {
  }

  guestCount: number;
  guests: Array<APIGuest> = new Array<APIGuest>();
  id: string;

  createPeople(count: number) {
    this.guestCount = count;
    this.guests = [];
    for (let n = 0; n < count; n++) {
      this.guests.push(new Guest());
    }
  }
}
