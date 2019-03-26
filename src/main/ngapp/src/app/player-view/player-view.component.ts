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

  connected: string = "Not Connected";

  constructor(private webSocketServiceConst: WebSocketService) {
    this.webSocketService = webSocketServiceConst;
  }

  ngOnInit(): void {
    let joinGame: JoinGame = new JoinGame();
    let _this: PlayerViewComponent = this;

    this.webSocketService.sendCallback(joinGame, function () {
      console.log("We got back the thing from our saved game");
      _this.connected = "We are connected!";
    });
  }
}
