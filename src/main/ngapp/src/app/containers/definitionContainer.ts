import {Entry} from "../wsObjects/entry";

export class DefinitionContainer {
  private _entry: Entry;

  constructor(entry: Entry) {
    this._entry = entry;
  }
}
