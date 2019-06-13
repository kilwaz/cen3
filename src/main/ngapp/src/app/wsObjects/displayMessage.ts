import {Message} from "./message";

export class DisplayMessage extends Message {

  private _message:string;

  constructor() {
    super();
    this.type = "DisplayMessage";
  }

  decodeResponse(msgRaw: any) {

  }

  set message(value: string) {
    this._message = value;
  }
}
