import {Component, OnInit} from '@angular/core';
import {WebSocketService} from "../websocket.service";
import {JoinGame} from "../wsObjects/joinGame";
import {Answer} from "../wsObjects/answer";

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

    this.webSocketService.sendCallback(joinGame, function (responseMessage) {
      let joinGame: JoinGame = <JoinGame>responseMessage;
      console.log(joinGame.type);
      console.log(joinGame.playerUUID);
    });
  }

  playerButtonPressed(button): void {
    console.log("Pressed a button woop " + button);

    let answer: Answer = new Answer();
    answer.answer = button;
    this.webSocketService.sendCallback(answer, function (responseMessage) {
      let answer: Answer = <Answer>responseMessage;
      console.log("callback answer");
    });
  }
}
