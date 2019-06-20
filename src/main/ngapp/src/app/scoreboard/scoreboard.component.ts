import {Component, Input, OnInit} from '@angular/core';
import {WebSocketService} from "../services/websocket.service";
import {Game} from "../game";
import {FullScoreboard} from "../wsObjects/fullScoreboard";
import {Player} from "../player";

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

    let _this: ScoreboardComponent = this;

    let fullScoreboard = new FullScoreboard();
    this.webSocketService.sendCallback(fullScoreboard, function (responseMessage) {
      let fullScoreboard: FullScoreboard = <FullScoreboard>responseMessage;
      for (let index in fullScoreboard.scores) {
        let score = fullScoreboard.scores[index];
        let player: Player = _this.game.findPlayer(score.playerUUID);
        if (player) {
          player.score = score.score;
        }
      }
      _this.game.sortPlayersByScore();
    });
  }

  ngOnInit() {
  }
}
