import {Component} from '@angular/core';
import {WebSocketService} from "./websocket.service";
import {Message} from "./wsObjects/message";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: []
})
export class AppComponent {
  chatMessage: string = "initial";
  webSocketService: WebSocketService;

  constructor(private webSocketServiceConst: WebSocketService) {
    this.webSocketService = webSocketServiceConst;

    this.webSocketService.send(new Message("ChatMessage", "Message 1 sent"));
  }

  sendMessage() {
    this.webSocketService.send(new Message("ChatMessage", this.chatMessage));
  }

  onKey(value: string) {
    this.chatMessage = value;
  }

  onEnter() {
    this.sendMessage();
  }

  closeConnection() {
    this.webSocketService.close();
  }
}
