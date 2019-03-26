import {Message} from "./message";

export class JoinGame extends Message {

  constructor() {
    super();
    this.type = "JoinGame";
  }
}
