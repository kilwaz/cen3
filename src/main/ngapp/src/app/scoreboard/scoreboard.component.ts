import {Component, Input, OnInit} from '@angular/core';
import {WebSocketService} from "../websocket.service";
import {Game} from "../game";

@Component({
  selector: 'app-scoreboard',
  templateUrl: './scoreboard.component.html',
  styleUrls: ['./scoreboard.component.css']
})
export class ScoreboardComponent implements OnInit {
  webSocketService: WebSocketService;
  webSocketServiceReference = WebSocketService;

  @Input() game: Game;

  constructor(private webSocketServiceConst: WebSocketService) {
    this.webSocketService = webSocketServiceConst;
  }

  ngOnInit() {
  }
}
