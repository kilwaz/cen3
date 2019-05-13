import {Injectable} from '@angular/core';
import {Message} from "./wsObjects/message";
import {webSocket} from "rxjs/webSocket";
import {NewPlayerJoined} from "./wsObjects/newPlayerJoined";
import {AnswerUpdate} from "./wsObjects/answerUpdate";
import {NextQuestion} from "./wsObjects/nextQuestion";

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {
  public ws: any;
  public static connected: boolean = false;
  private static callBacks: { [key: string]: (Message) => any } = {};
  private static callObjs: { [key: string]: Message } = {};

  private static actionClasses = new Map();

  constructor() {
    WebSocketService.actionClasses.set("NewPlayerJoined", NewPlayerJoined.constructor);
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
    if (msgRaw.hasOwnProperty('callBackUUID')) { // Response from the server from generated client request
      let callback = this.callBacks[msgRaw.callBackUUID];
      let callObjs = this.callObjs[msgRaw.callBackUUID];

      // Off load the response message into the client side message object
      callObjs.decodeResponse(msgRaw);

      if (callback != undefined) {
        // Call the correct callback with the updated message object which should now contain all expected response values
        callback(callObjs);

        // Clean up the callbacks array once the callback has been completed
        this.callBacks[msgRaw.callBackUUID] = undefined;
      }
    } else { // Push from the server
      if (msgRaw.hasOwnProperty('type')) { // Handle a pushed action
        if (msgRaw.type == "NewPlayerJoined") {
          let actionMessage = new NewPlayerJoined();
          actionMessage.decodeResponse(msgRaw);
          NewPlayerJoined.informListeners(actionMessage);
        }
        if (msgRaw.type == "AnswerUpdate") {
          let actionMessage = new AnswerUpdate();
          actionMessage.decodeResponse(msgRaw);
          AnswerUpdate.informListeners(actionMessage);
        }
        if (msgRaw.type == "NextQuestion") {
          let actionMessage = new NextQuestion();
          actionMessage.decodeResponse(msgRaw);
          NextQuestion.informListeners(actionMessage);
        }
      }
    }
    console.log(msgRaw);
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

  sendCallback(message: Message, callback: (Message) => any) {
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
