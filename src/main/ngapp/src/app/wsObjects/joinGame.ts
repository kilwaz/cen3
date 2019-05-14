import {Message} from "./message";

export class JoinGame extends Message {

  private _playerUUID: string;
  private _playerID: number;
  private _playerName: string;

  private _localStorageUUID: string;

  constructor() {
    super();
    this.type = "JoinGame";
  }

  decodeResponse(msgRaw: any) {
    this._playerUUID = msgRaw.playerUUID;
    this._playerID = msgRaw.playerID;
    this._playerName = msgRaw.playerName;
  }

  get playerUUID(): string {
    return this._playerUUID;
  }

  get playerID(): number {
    return this._playerID;
  }

  get localStorageUUID(): string {
    return this._localStorageUUID;
  }

  set localStorageUUID(value: string) {
    this._localStorageUUID = value;
  }

  get playerName(): string {
    return this._playerName;
  }

  set playerName(value: string) {
    this._playerName = value;
  }
}
