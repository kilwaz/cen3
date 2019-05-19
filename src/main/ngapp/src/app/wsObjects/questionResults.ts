import {Message} from "./message";
import {QuestionOption} from "../questionOption";

export class QuestionResults extends Message {

  private _questionUUID: string;
  private _correctOptionUUID: string;
  private _questionOptions: Array<QuestionOption> = [];

  constructor() {
    super();
    this.type = "QuestionResults";
  }

  decodeResponse(msgRaw: any) {
    this._questionUUID = msgRaw.questionUUID;
    this._questionOptions = msgRaw.options;
    this._correctOptionUUID = msgRaw.correctOptionUUID;
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
}
