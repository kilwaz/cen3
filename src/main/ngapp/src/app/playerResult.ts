export class PlayerResult {
  private _playerUUID: string;
  private _isCorrectAnswer: string;

  constructor() {
  }

  get playerUUID(): string {
    return this._playerUUID;
  }

  set playerUUID(value: string) {
    this._playerUUID = value;
  }

  get isCorrectAnswer(): string {
    return this._isCorrectAnswer;
  }

  set isCorrectAnswer(value: string) {
    this._isCorrectAnswer = value;
  }
}
