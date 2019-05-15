export class ScoreUpdate {
  private _playerUUID: string;
  private _score: number = 0;

  constructor() {

  }

  get playerUUID(): string {
    return this._playerUUID;
  }

  set playerUUID(value: string) {
    this._playerUUID = value;
  }

  get score(): number {
    return this._score;
  }

  set score(value: number) {
    this._score = value;
  }
}
