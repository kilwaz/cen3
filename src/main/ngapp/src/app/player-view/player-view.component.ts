import {Component, OnInit} from '@angular/core';
import {WebSocketService} from "../websocket.service";
import {JoinGame} from "../wsObjects/joinGame";
import {AnswerResponse} from "../wsObjects/answerResponse";
import {Player} from "../player";

@Component({
  selector: 'app-player-view',
  templateUrl: './player-view.component.html',
  styleUrls: ['./player-view.component.css']
})
export class PlayerViewComponent implements OnInit {
  webSocketService: WebSocketService;
  webSocketServiceReference = WebSocketService;

  private player: Player;

  constructor(private webSocketServiceConst: WebSocketService) {
    this.webSocketService = webSocketServiceConst;
  }

  ngOnInit(): void {
    let joinGame: JoinGame = new JoinGame();
    joinGame.localStorageUUID = window.localStorage.getItem("playerUUID");
    let _this: PlayerViewComponent = this;

    this.webSocketService.sendCallback(joinGame, function (responseMessage) {
      let joinGame: JoinGame = <JoinGame>responseMessage;
      let player = new Player(joinGame.playerUUID);
      player.id = joinGame.playerID;
      window.localStorage.setItem("playerUUID", player.uuid);
      _this.player = player;
    });
  }

  playerButtonPressed(button): void {
    let answer: AnswerResponse = new AnswerResponse();
    answer.answerValue = button;
    answer.playerUUID = this.player.uuid;
    this.webSocketService.sendCallback(answer, function (responseMessage) {
      let answerResponse: AnswerResponse = <AnswerResponse>responseMessage;
      console.log("callback answer");
    });
  }
}
