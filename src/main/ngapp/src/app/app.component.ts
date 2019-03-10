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
  constructor(private webSocketService: WebSocketService) {
    webSocketService.send(new Message("Message 1 sent"));
    webSocketService.send(new Message("Message 2 sent"));
  }
}
