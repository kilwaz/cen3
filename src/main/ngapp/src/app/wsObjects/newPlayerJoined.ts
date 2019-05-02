import {Message} from "./message";

export class NewPlayerJoined extends Message {

  private _newPlayerUUID: string;

  constructor() {
    super();
    this.type = "NewPlayerJoined";
    console.log("New player joined action created...");
  }

  decodeResponse(msgRaw: any) {
    this._newPlayerUUID = msgRaw.newPlayerUUID;
  }

  get newPlayerUUID(): string {
    return this._newPlayerUUID;
  }
}
