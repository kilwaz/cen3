import {Message} from "./message";
import {QuestionOption} from "../questionOption";

export class NextQuestion extends Message {

  private _questionUUID: string;
  private _questionText: string;
  private _questionOptions: Array<QuestionOption> = [];

  constructor() {
    super();
    this.type = "NextQuestion";
  }

  decodeResponse(msgRaw: any) {
    this._questionUUID = msgRaw.nextQuestionUUID;
    this._questionOptions = msgRaw.options;
    this._questionText = msgRaw.questionText;
  }

  get questionUUID(): string {
    return this._questionUUID;
  }

  get questionOptions(): Array<QuestionOption> {
    return this._questionOptions;
  }

  get questionText(): string {
    return this._questionText;
  }
}
