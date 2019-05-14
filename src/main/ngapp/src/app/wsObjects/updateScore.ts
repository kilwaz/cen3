import {Message} from "./message";

export class UpdateScore extends Message {
  private _playerUUID: string;
  private _score: number;

  constructor() {
    super();
    this.type = "UpdateScore";
  }

  decodeResponse(msgRaw: any) {
    this._playerUUID = msgRaw.playerUUID;
    this._score = msgRaw.score;
  }

  get playerUUID(): string {
    return this._playerUUID;
  }

  get score(): number {
    return this._score;
  }
}
