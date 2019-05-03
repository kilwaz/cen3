import {Message} from "./message";

export class AnswerResponse extends Message {

  private _answerValue: string;
  private _playerUUID: string;

  constructor() {
    super();
    this.type = "AnswerResponse";
  }

  decodeResponse(msgRaw: any) {
    console.log("AnswerResponse responded");
  }

  get answerValue(): string {
    return this._answerValue;
  }

  set answerValue(value: string) {
    this._answerValue = value;
  }

  get playerUUID(): string {
    return this._playerUUID;
  }

  set playerUUID(value: string) {
    this._playerUUID = value;
  }
}
