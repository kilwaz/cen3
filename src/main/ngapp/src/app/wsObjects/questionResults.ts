import {Message} from "./message";
import {QuestionOption} from "../questionOption";
import {PlayerResult} from "../playerResult";

export class QuestionResults extends Message {

  private _questionUUID: string;
  private _correctOptionUUID: string;
  private _questionOptions: Array<QuestionOption> = [];
  private _playerResults: Array<PlayerResult> = [];

  constructor() {
    super();
    this.type = "QuestionResults";
  }

  decodeResponse(msgRaw: any) {
    this._questionUUID = msgRaw.questionUUID;
    this._questionOptions = msgRaw.options;
    this._correctOptionUUID = msgRaw.correctOptionUUID;
    this._playerResults = msgRaw.playerResults;
  }

  get questionUUID(): string {
    return this._questionUUID;
  }

  get correctOptionUUID(): string {
    return this._correctOptionUUID;
  }

  get questionOptions(): Array<QuestionOption> {
    return this._questionOptions;
  }

  get playerResults(): Array<PlayerResult> {
    return this._playerResults;
  }
}
