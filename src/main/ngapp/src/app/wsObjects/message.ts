import {Guid} from "guid-typescript";

export class Message {
  private _type: string;
  private _callbackUUID: string;

  constructor() {
    this._callbackUUID = Guid.create().toString();
  }

  decodeResponse(msgRaw: any) {
    console.log("Hello this was a thing");
  }

  set type(value: string) {
    this._type = value;
  }

  get callbackUUID(): string {
    return this._callbackUUID;
  }
}
