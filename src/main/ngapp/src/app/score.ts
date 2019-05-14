import {Player} from "./player";

export class Score {
  private readonly _player: Player;
  private _score: number = 0;

  constructor(player: Player) {
    this._player = player;
  }

  get player(): Player {
    return this._player;
  }

  set score(value: number) {
    this._score = value;
  }

  get score(): number {
    return this._score;
  }
}
