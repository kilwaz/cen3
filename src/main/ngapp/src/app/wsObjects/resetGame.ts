import {Message} from "./message";

export class ResetGame extends Message {

  constructor() {
    super();
    this.type = "ResetGame";
  }

  decodeResponse(msgRaw: any) {

  }
}
