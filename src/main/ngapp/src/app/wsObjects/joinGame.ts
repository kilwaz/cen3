import {Message} from "./message";

export class JoinGame extends Message {

  constructor() {
    super();
    this.type = "JoinGame";
  }

  decodeResponse(msgRaw: any) {
    console.log("Hello this was a thing within the ");
  }
}
