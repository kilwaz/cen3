import {Message} from "./message";

export class PlayerNameChange extends Message {
  private _playerUUID: string;
  private _playerName: string;

  constructor() {
    super();
    this.type = "PlayerNameChange";
  }

  get playerUUID(): string {
    return this._playerUUID;
  }

  set playerUUID(value: string) {
    this._playerUUID = value;
  }

  get playerName(): string {
    return this._playerName;
  }

  set playerName(value: string) {
    this._playerName = value;
  }
}
