import {Message} from "./message";

export class ClearGameScreen extends Message {
  private _message: string;

  constructor() {
    super();
    this.type = "ClearGameScreen";
  }

  decodeResponse(msgRaw: any) {
    this._message = msgRaw.message;
  }

  get message(): string {
    return this._message;
  }
}
