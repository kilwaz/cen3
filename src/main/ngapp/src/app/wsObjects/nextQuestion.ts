import {Message} from "./message";
import {QuestionOption} from "../questionOption";

export class NextQuestion extends Message {

  private _questionUUID: string;
  private _questionOptions: Array<QuestionOption> = [];

  constructor() {
    super();
    this.type = "NextQuestion";
  }

  decodeResponse(msgRaw: any) {
    this._questionUUID = msgRaw.nextQuestionUUID;
    this._questionOptions = msgRaw.options;
  }

  get questionUUID(): string {
    return this._questionUUID;
  }

  get questionOptions(): Array<QuestionOption> {
    return this._questionOptions;
  }
}
