import {Message} from "./message";

export class ClearScreen extends Message {

  constructor() {
    super();
    this.type = "ClearScreen";
  }

  decodeResponse(msgRaw: any) {

  }
}
