import {Person} from "./person";

export class Rsvp {

  private _numberOfPeople: number = 0;
  private _people: Person[] = [];

  constructor() {
  }

  get people(): Person[] {
    return this._people;
  }

  get numberOfPeople(): number {
    return this._numberOfPeople;
  }

  set numberOfPeople(value: number) {
    this._numberOfPeople = value;
  }

  createPeople(count: number) {
    this.numberOfPeople = count;
    this._people = [];
    for (let n = 0; n < count; n++) {
      this._people.push(new Person());
    }
  }
}
