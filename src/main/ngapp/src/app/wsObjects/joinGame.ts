import {Message} from "./message";

export class JoinGame extends Message {

  private _playerUUID: string;

  constructor() {
    super();
    this.type = "JoinGame";
  }

  decodeResponse(msgRaw: any) {
    this._playerUUID = msgRaw.playerUUID;
  }

  get playerUUID(): string {
    return this._playerUUID;
  }
}
