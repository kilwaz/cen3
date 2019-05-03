import {Answer} from "./answer";

export class Player {
  uuid: string;
  private _latestAnswer: Answer;

  constructor(uuid: string) {
    this.uuid = uuid;
  }

  get latestAnswer(): Answer {
    return this._latestAnswer;
  }

  set latestAnswer(value: Answer) {
    this._latestAnswer = value;
  }
}
