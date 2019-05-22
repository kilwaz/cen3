export class QuestionOption {
  private _optionUUID: string;
  private _optionAnswer: string;
  private _optionAnswerCount: number;
  private _optionPercentageOfTotalAnswers: number;
  private _answerProgressClass: string;
  private _isCorrectAnswer: boolean;

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

  get optionAnswerCount(): number {
    return this._optionAnswerCount;
  }

  set optionAnswerCount(value: number) {
    this._optionAnswerCount = value;
  }

  get optionPercentageOfTotalAnswers(): number {
    return this._optionPercentageOfTotalAnswers;
  }

  set optionPercentageOfTotalAnswers(value: number) {
    this._optionPercentageOfTotalAnswers = value;
  }

  get answerProgressClass(): string {
    return this._answerProgressClass;
  }

  set answerProgressClass(value: string) {
    this._answerProgressClass = value;
  }

  get isCorrectAnswer(): boolean {
    return this._isCorrectAnswer;
  }

  set isCorrectAnswer(value: boolean) {
    this._isCorrectAnswer = value;
  }
}
