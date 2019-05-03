export class Answer {
  uuid: string;
  private _answerValue: string;

  constructor(uuid: string) {
    this.uuid = uuid;
  }

  get answerValue(): string {
    return this._answerValue;
  }

  set answerValue(value: string) {
    this._answerValue = value;
  }
}
