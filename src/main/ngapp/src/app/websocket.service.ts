import {Injectable} from '@angular/core';
import {Message} from "./wsObjects/message";
import {webSocket} from "rxjs/webSocket";

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {
  public ws: any;
  public static connected: boolean = false;

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
    console.log('message received: ' + msgRaw);

    // let message: Message = JSON.parse(msgRaw);
  }


  private static error(err: any) {
    console.log('error happened ' + err);
  }

  private static complete() {
    console.log('complete');
    this.connected = false;
  }

  close() {
    this.ws.complete();
  }

  send(message: Message) {
    if (!WebSocketService.connected) {
      console.log("The connection is closed...");
      this.buildSocket();
    }

    this.ws.next(JSON.stringify(message));
  }
}
