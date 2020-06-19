import {Injectable} from '@angular/core';
import {Message} from '../wsActions/message';
import {webSocket} from 'rxjs/webSocket';
import {ClassDictionary} from '../classDictionary';

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {
  public static connected = false;
  private static callBacks: { [key: string]: (Message) => any } = {};
  private static callObjs: { [key: string]: Message } = {};

  public ws: any;

  constructor() {
    // Need to send some sort of initial message to the server here...
    // WebSocketService.actionClasses.set("NewPlayerJoined", NewPlayerJoined.constructor);
    this.buildSocket();
  }

  private static complete() {
    console.log('Websocket connection closed');
    WebSocketService.connected = false;
  }

  static close() {
    WebSocketService.complete();
  }

  private static received(msgRaw: any) {
    if (msgRaw.type !== 'HeartBeat') { // Don't report on HeartBeat messages
      console.log(msgRaw);
    }

    if (msgRaw.hasOwnProperty('callBackUUID')) { // Response from the server from generated client request
      const callback = this.callBacks[msgRaw.callBackUUID];
      const callObjs = this.callObjs[msgRaw.callBackUUID];

      // Off load the response message into the client side message object
      callObjs.decodeResponse(msgRaw);

      if (callback !== undefined) {
        // Call the correct callback with the updated message object which should now contain all expected response values
        callback(callObjs);

        // Clean up the callbacks array once the callback has been completed
        this.callBacks[msgRaw.callBackUUID] = undefined;
      }
    } else { // Push from the server
      if (msgRaw.hasOwnProperty('type')) { // Handle a pushed action
        // Find action from dictionary
        const actionClass: any = ClassDictionary.getClass(msgRaw.type);
        if (actionClass !== undefined) { // Check to see if action exists
          // New action object
          const actionMessage: Message = new actionClass();
          // decode server response into object
          actionMessage.decodeResponse(msgRaw);
          // Send object to any listeners that are listening
          actionClass.informListeners(actionMessage);
        }
      }
    }
  }

  private static error(err: any) {
    console.log('Websocket error happened ' + err);
    console.log('Code ' + err.code);

    if (err.type === 'close' || err.type === 'error') {
      WebSocketService.complete();
    }

    if (err.code === 1006) {
      console.log('Server has gone away');
    } else if (err.code === undefined) {
      console.log('Server could not be found');
    }
  }

  private buildSocket() {
    // this.ws = webSocket("ws://192.168.1.26:4568/ws"); // If hosting on another machine this needs to be set correctly
    this.ws = webSocket('ws://localhost:4568/ws'); // Local Dev IP
    this.ws.subscribe(
      msg => WebSocketService.received(msg),
      err => WebSocketService.error(err),
      () => WebSocketService.complete()
    );
    this.ws.subscribe();
    WebSocketService.connected = true;
  }

  sendCallback(message: Message, callback: (Message) => any) {
    WebSocketService.callBacks[message.callBackUUID] = callback;
    WebSocketService.callObjs[message.callBackUUID] = message;

    this.send(message);
  }

  send(message: Message) {
    if (!WebSocketService.connected) {
      console.log('The connection is closed.');
      this.buildSocket();
    }

    this.ws.next(JSON.stringify(message));
  }
}
