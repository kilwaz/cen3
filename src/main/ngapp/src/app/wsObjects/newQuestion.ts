import {Message} from "./message";

export class NewQuestion extends Message {
  constructor() {
    super();
    this.type = "NewQuestion";
  }

  decodeResponse(msgRaw: any) {

  }
}
