import {Component, OnInit} from '@angular/core';
import {WebSocketService} from "../websocket.service";
import {AdminGame} from "../wsObjects/adminGame";

@Component({
  selector: 'app-game-view',
  templateUrl: './game-view.component.html',
  styleUrls: ['./game-view.component.css']
})
export class GameViewComponent implements OnInit {
  webSocketService: WebSocketService;
  webSocketServiceReference = WebSocketService;

  constructor(private webSocketServiceConst: WebSocketService) {
    this.webSocketService = webSocketServiceConst;
  }

  ngOnInit(): void {
    let adminGame: AdminGame = new AdminGame();
    // let _this: GameViewComponent = this;

    this.webSocketService.sendCallback(adminGame, function () {

    });
  }
}
