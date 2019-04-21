import {Injectable} from '@angular/core';
import {Message} from "./wsObjects/message";
import {webSocket} from "rxjs/webSocket";

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {
  public ws: any;
  public static connected: boolean = false;
  private static callBacks: { [key: string]: () => any } = {};
  private static callObjs: { [key: string]: Message } = {};

  constructor() {
    this.buildSocket();
  }

  private buildSocket() {
    this.ws = webSocket("ws://localhost:4568/ws");
    this.ws.subscribe(
      msg => WebSocketService.received(msg),
      err => WebSocketService.error(err),
      () => WebSocketService.complete()
    );
    this.ws.subscribe();
    WebSocketService.connected = true;
  }

  private static received(msgRaw: any) {
    console.log('message player uuid: ' + msgRaw.playerUUID);
    console.log('message callback uuid: ' + msgRaw.callBackUUID);
    console.log('message type: ' + msgRaw.type);

    let callback = this.callBacks[msgRaw.callBackUUID];
    let callObjs = this.callObjs[msgRaw.callBackUUID];

    // let obj;
    // eval("obj=new App" + msgRaw.type + "()");
    // let joinGame: JoinGame = <JoinGame>obj;
    //
    // joinGame.decode();

    if (callback != undefined) {
      callback();

      this.callBacks[msgRaw.callBackUUID] = undefined;
    }
  }

  private static error(err: any) {
    console.log('Websocket error happened ' + err);
    console.log('Code ' + err.code);

    if (err.type == "close" || err.type == "error") {
      WebSocketService.complete();
    }

    if (err.code == 1006) {
      console.log("Server has gone away");
    } else if (err.code == undefined) {
      console.log("Server could not be found");
    }
  }

  private static complete() {
    console.log('Websocket connection closed');
    WebSocketService.connected = false;
  }

  static close() {
    WebSocketService.complete();
  }

  sendCallback(message: Message, callback: () => any) {
    WebSocketService.callBacks[message.callbackUUID] = callback;
    WebSocketService.callObjs[message.callbackUUID] = message;

    this.send(message);
  }

  send(message: Message) {
    if (!WebSocketService.connected) {
      console.log("The connection is closed...");
      this.buildSocket();
    }

    this.ws.next(JSON.stringify(message));
  }
}
