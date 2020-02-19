import {Record} from "../wsObjects/record";
import {Entry} from "../wsObjects/entry";

export class RecordContainer {
  private readonly _record: Record;
  private _entryReferenceMap: Map<string, Entry>;

  constructor(record: Record) {
    this._record = record;
    this._entryReferenceMap = new Map<string, Entry>();

    let entryReferenceMap = this._entryReferenceMap;

    if (record.entries) {
      record.entries.forEach(function (entry) {
        entryReferenceMap.set(entry.name.toLowerCase(), entry);
      });
    }
  }

  updateEntry(entry: Entry): void {
    if (this._record.entries === undefined) {
      this._record.entries = [];
    }
    let currentEntry: Entry = this.entryReferenceMap.get(entry.name.toLowerCase());
    if (currentEntry) {
      currentEntry.value = entry.value;
    } else {
      this._record.entries.push(entry);
      this.entryReferenceMap.set(entry.name.toLowerCase(), entry);
    }
  }

  updateEntries(entries: Array<Entry>): void {
    let _this: RecordContainer = this;

    entries.forEach(function (entry) {
      _this.updateEntry(entry);
    });
  }

  get record(): Record {
    return this._record;
  }

  getEntry(reference: string): Entry {
    return this._entryReferenceMap.get(reference.toLowerCase());
  }

  get entryReferenceMap(): Map<string, Entry> {
    return this._entryReferenceMap;
  }
}
