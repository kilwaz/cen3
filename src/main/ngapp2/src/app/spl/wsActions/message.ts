import {Guid} from 'guid-typescript';

export class Message {
  private static classListeners: Array<(Message) => void> = [];

  private _type: string;
  private readonly _callBackUUID: string;

  constructor() {
    this._callBackUUID = Guid.create().toString();
  }

  public static registerListener(type: string, func: (Message) => void) {
    const listeners = this.classListeners[type];
    if (listeners === undefined) {
      this.classListeners[type] = new Array<(Message) => void>();

    }
    this.classListeners[type].push(func);
  }

  public static informListeners(message: Message) {
    const listeners = this.classListeners[message.type];

    if (listeners !== undefined) {
      for (const func of listeners) {
        func(message);
      }
    }
  }

  decodeResponse(msgRaw: any) {
    // Overridden by child class
  }

  get type(): string {
    return this._type;
  }

  set type(value: string) {
    this._type = value;
  }

  get callBackUUID(): string {
    return this._callBackUUID;
  }
}
