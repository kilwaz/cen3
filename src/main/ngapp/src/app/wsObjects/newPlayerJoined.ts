import {Message} from "./message";

export class NewPlayerJoined extends Message {

  private _newPlayerUUID: string;
  private _newPlayerID: number;

  constructor() {
    super();
    this.type = "NewPlayerJoined";
  }

  decodeResponse(msgRaw: any) {
    this._newPlayerUUID = msgRaw.newPlayerUUID;
    this._newPlayerID = msgRaw.newPlayerID;
  }

  get newPlayerUUID(): string {
    return this._newPlayerUUID;
  }

  get newPlayerID(): number {
    return this._newPlayerID;
  }
}
