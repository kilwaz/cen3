import {Guid} from "guid-typescript";

export class Message {
  private _type: string;
  private _callbackUUID: string;

  constructor() {
    this._callbackUUID = Guid.create().toString();
  }

  set type(value: string) {
    this._type = value;
  }

  get callbackUUID(): string {
    return this._callbackUUID;
  }
}
