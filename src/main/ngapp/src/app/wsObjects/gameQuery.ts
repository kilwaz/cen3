import {Message} from "./message";

export class GameQuery extends Message {
  private _message: string;

  constructor() {
    super();
    this.type = "GameQuery";
  }

  set message(value: string) {
    this._message = value;
  }
}
