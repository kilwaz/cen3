import {Guid} from "guid-typescript";

export class Message {
  private _type: string;
  private readonly _callbackUUID: string;

  private static classListeners: Array<(Message) => void> = [];

  constructor() {
    this._callbackUUID = Guid.create().toString();
  }

  decodeResponse(msgRaw: any) {
    // Overridden by child class
  }

  set type(value: string) {
    this._type = value;
  }

  get callbackUUID(): string {
    return this._callbackUUID;
  }

  get type(): string {
    return this._type;
  }

  public static registerListener(type: string, func: (Message) => void) {
    let listeners = this.classListeners[type];
    if (listeners == undefined) {
      this.classListeners[type] = new Array<(Message) => void>();

    }
    this.classListeners[type].push(func);
  }

  public static informListeners(message: Message) {
    let listeners = this.classListeners[message.type];

    for (let func of listeners) {
      func(message);
    }
  }
}
