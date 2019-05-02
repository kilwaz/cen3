import {Guid} from "guid-typescript";

export class Message {
  private _type: string;
  private readonly _callbackUUID: string;

  private static listeners = new Array<(Message) => void>();

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

  public static registerListener(func: (Message) => void) {
    this.listeners.push(func);
  }

  public static informListeners(message:Message ) {
    for (let func of this.listeners) {
      func(message);
    }
  }
}
