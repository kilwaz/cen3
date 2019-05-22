import {Answer} from "./answer";

export class Player {
  private _uuid: string;
  private _latestAnswer: Answer;
  private _id: number;
  private _score: number = 0;
  private _name: string;
  private _playerStatus: string = "alert-light";

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

  get score(): number {
    return this._score;
  }

  set score(value: number) {
    this._score = value;
  }

  get name(): string {
    return this._name;
  }

  set name(value: string) {
    this._name = value;
  }

  get playerStatus(): string {
    return this._playerStatus;
  }

  set playerStatus(value: string) {
    this._playerStatus = value;
  }
}
