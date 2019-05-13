export class QuestionOption {
  private _optionUUID: string;
  private _optionAnswer: string;

  constructor(uuid: string) {
    this._optionUUID = uuid;
  }

  get optionAnswer(): string {
    return this._optionAnswer;
  }

  get optionUUID(): string {
    return this._optionUUID;
  }

  set optionUUID(value: string) {
    this._optionUUID = value;
  }

  set optionAnswer(value: string) {
    this._optionAnswer = value;
  }
}
