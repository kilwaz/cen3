import {Message} from "./message";

export class AnswerUpdate extends Message {

  private _answerUUID: string;
  private _playerUUID: string;
  private _answerValue: string;

  constructor() {
    super();
    this.type = "AnswerUpdate";
  }

  decodeResponse(msgRaw: any) {
    this._answerUUID = msgRaw.answerUUID;
    this._playerUUID = msgRaw.playerUUID;
    this._answerValue = msgRaw.answerValue;
  }

  get answerUUID(): string {
    return this._answerUUID;
  }

  get playerUUID(): string {
    return this._playerUUID;
  }

  get answerValue(): string {
    return this._answerValue;
  }
}
