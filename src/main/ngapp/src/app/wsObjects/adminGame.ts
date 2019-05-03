import {Message} from "./message";

export class AdminGame extends Message {

  private _adminUUID: string;

  constructor() {
    super();
    this.type = "AdminGame";
  }

  decodeResponse(msgRaw: any) {
    this._adminUUID = msgRaw.adminUUID;
  }

  get adminUUID(): string {
    return this._adminUUID;
  }
}
