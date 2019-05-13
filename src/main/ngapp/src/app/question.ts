import {QuestionOption} from "./questionOption";

export class Question {
  private _uuid: string;
  private _questionOptions: Array<QuestionOption> = [];

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
}
