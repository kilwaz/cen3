import {Component, OnInit} from '@angular/core';
import {WebSocketService} from "../websocket.service";
import {JoinGame} from "../wsObjects/joinGame";

@Component({
  selector: 'app-player-view',
  templateUrl: './player-view.component.html',
  styleUrls: ['./player-view.component.css']
})
export class PlayerViewComponent implements OnInit {
  webSocketService: WebSocketService;

  webSocketServiceReference = WebSocketService;

  constructor(private webSocketServiceConst: WebSocketService) {
    this.webSocketService = webSocketServiceConst;
  }

  ngOnInit(): void {
    let joinGame: JoinGame = new JoinGame();
    // let _this: PlayerViewComponent = this;

    this.webSocketService.sendCallback(joinGame, function () {

    });
  }
}
