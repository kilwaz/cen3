import {Message} from "./message";

export class TriggerRoundEnd extends Message {

  constructor() {
    super();
    this.type = "TriggerRoundEnd";
  }

  decodeResponse(msgRaw: any) {

  }
}
