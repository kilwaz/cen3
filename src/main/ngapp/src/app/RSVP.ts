import {Guest} from "./guest";
import {APIRSVP} from "./model/aPIRSVP";

export class RSVP implements APIRSVP {
  constructor() {
  }

  guestCount: number;
  guests: Array<Guest> = new Array<Guest>();
  id: string;

  createPeople(count: number, rsvpType: number) {
    this.guestCount = count;
    this.guests = [];
    for (let n = 0; n < count; n++) {
      this.guests.push(new Guest(rsvpType));
    }
  }

  getIsValidData() {
    let valid: boolean = true;
    for (let n = 0; n < this.guests.length; n++) {
      if (!this.guests[n].getIsValidData()) {
        return false;
      }
    }

    return valid;
  }
}
