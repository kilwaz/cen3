import {Entry} from "../wsObjects/entry";

export class EntryContainer {
  private _entry: Entry;

  constructor(entry: Entry) {
    this._entry = entry;
  }
}
