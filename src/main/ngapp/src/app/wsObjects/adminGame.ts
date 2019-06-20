import {Message} from "./message";
import {Player} from "../player";

export class AdminGame extends Message {

  private _adminUUID: string;
  private _players: Array<Player> = [];

  private _localStorageUUID: string;

  constructor() {
    super();
    this.type = "AdminGame";
  }

  decodeResponse(msgRaw: any) {
    this._adminUUID = msgRaw.adminUUID;
    this._players = msgRaw.players;
  }

  get adminUUID(): string {
    return this._adminUUID;
  }

  get localStorageUUID(): string {
    return this._localStorageUUID;
  }

  set localStorageUUID(value: string) {
    this._localStorageUUID = value;
  }

  get players(): Array<Player> {
    return this._players;
  }
}
