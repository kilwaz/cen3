import {Message} from "./message";

export class Answer extends Message {

  private _answer: string;

  constructor() {
    super();
    this.type = "Answer";
  }

  decodeResponse(msgRaw: any) {
    console.log("Answer responded");
  }

  get answer(): string {
    return this._answer;
  }

  set answer(value: string) {
    this._answer = value;
  }
}
