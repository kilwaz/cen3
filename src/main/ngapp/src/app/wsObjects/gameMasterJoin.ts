import {Message} from "./message";
import {ScoreUpdate} from "../scoreUpdate";
import {Player} from "../player";

export class GameMasterJoin extends Message {

  private _gameMasterUUID: string;
  private _players: Array<Player> = [];

  private _localStorageUUID: string;

  constructor() {
    super();
    this.type = "GameMasterJoin";
  }

  decodeResponse(msgRaw: any) {
    this._gameMasterUUID = msgRaw.gameMasterUUID;
    this._players = msgRaw.players;
  }

  get gameMasterUUID(): string {
    return this._gameMasterUUID;
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
