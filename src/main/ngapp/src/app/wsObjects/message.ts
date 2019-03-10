export class Message {
  message: string;
  type: string;

  constructor(type: string, message: string) {
    this.type = type;
    this.message = message;
  }
}
