import {Component, OnInit} from '@angular/core';
import {WebSocketService} from "../websocket.service";
import {GameQuery} from "../wsObjects/gameQuery";

@Component({
  selector: 'app-game-view',
  templateUrl: './game-view.component.html',
  styleUrls: ['./game-view.component.css']
})
export class GameViewComponent implements OnInit {
  chatMessage: string = "initial";
  webSocketService: WebSocketService;

  constructor(private webSocketServiceConst: WebSocketService) {
    this.webSocketService = webSocketServiceConst;
  }

  sendMessage() {
    // this.webSocketService.send(new Message("ChatMessage", this.chatMessage));
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

  ngOnInit(): void {
    let gameQuery: GameQuery = new GameQuery();
    gameQuery.message = "New message way of doing game query?";
    this.webSocketService.send(gameQuery);
  }
}
