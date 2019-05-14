import {Message} from "./message";

export class PlayerNameUpdate extends Message {
  private _playerUUID: string;
  private _newName: string;

  constructor() {
    super();
    this.type = "PlayerNameUpdate";
  }

  decodeResponse(msgRaw: any) {
    this._playerUUID = msgRaw.playerUUID;
    this._newName = msgRaw.newName;
  }

  get playerUUID(): string {
    return this._playerUUID;
  }

  get newName(): string {
    return this._newName;
  }
}
