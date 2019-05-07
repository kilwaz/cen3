import {Answer} from "./answer";

export class Player {
  private _uuid: string;
  private _latestAnswer: Answer;
  private _id: number;

  constructor(uuid: string) {
    this._uuid = uuid;
  }

  get latestAnswer(): Answer {
    return this._latestAnswer;
  }

  set latestAnswer(value: Answer) {
    this._latestAnswer = value;
  }

  get uuid(): string {
    return this._uuid;
  }

  set uuid(value: string) {
    this._uuid = value;
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }
}
