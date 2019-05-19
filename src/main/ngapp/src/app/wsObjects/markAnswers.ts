import {Message} from "./message";

export class MarkAnswers extends Message {

  constructor() {
    super();
    this.type = "MarkAnswers";
  }

  decodeResponse(msgRaw: any) {

  }
}
