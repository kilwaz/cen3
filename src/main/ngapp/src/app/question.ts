import {QuestionOption} from "./questionOption";

export class Question {
  private _uuid: string;
  private _questionText: string;
  private _questionOptions: Array<QuestionOption> = [];
  private _totalAnswers: number = 0;

  constructor(uuid: string) {
    this._uuid = uuid;
  }

  get uuid(): string {
    return this._uuid;
  }

  set uuid(value: string) {
    this._uuid = value;
  }

  addQuestionOption(questionOption: QuestionOption) {
    this._questionOptions.push(questionOption);
  }

  get questionOptions(): Array<QuestionOption> {
    return this._questionOptions;
  }

  findQuestionOption(uuid: string): QuestionOption {
    for (let option of this._questionOptions) {

      if (option.optionUUID == uuid) {
        return option;
      }
    }
    return undefined;
  }

  get questionText(): string {
    return this._questionText;
  }

  set questionText(value: string) {
    this._questionText = value;
  }

  get totalAnswers(): number {
    return this._totalAnswers;
  }

  set totalAnswers(value: number) {
    this._totalAnswers = value;
  }
}
