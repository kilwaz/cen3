import {Message} from "./message";

export class AdminGame extends Message {

  constructor() {
    super();
    this.type = "AdminGame";
  }
}
