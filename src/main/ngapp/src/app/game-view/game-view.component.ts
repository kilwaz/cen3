import {Component, OnInit} from '@angular/core';
import {WebSocketService} from "../websocket.service";
import {AdminGame} from "../wsObjects/adminGame";
import {NewPlayerJoined} from "../wsObjects/newPlayerJoined";
import {Message} from "../wsObjects/message";
import {Game} from "../game";
import {Player} from "../player";
import {AnswerUpdate} from "../wsObjects/answerUpdate";
import {Answer} from "../answer";
import {NewQuestion} from "../wsObjects/newQuestion";
import {UpdateScore} from "../wsObjects/updateScore";

@Component({
  selector: 'app-game-view',
  templateUrl: './game-view.component.html',
  styleUrls: ['./game-view.component.css']
})
export class GameViewComponent implements OnInit {
  webSocketService: WebSocketService;
  webSocketServiceReference = WebSocketService;

  private game: Game;

  constructor(private webSocketServiceConst: WebSocketService) {
    this.webSocketService = webSocketServiceConst;
  }

  ngOnInit(): void {
    let _this: GameViewComponent = this;

    let adminGame = new AdminGame();
    adminGame.localStorageUUID = window.localStorage.getItem("adminUUID");

    this.webSocketService.sendCallback(adminGame, function () {
      _this.game = new Game(adminGame.adminUUID);
      window.localStorage.setItem("adminUUID", adminGame.adminUUID);
    });

    NewPlayerJoined.registerListener("NewPlayerJoined", function (message: Message) {
      let newPlayerJoined: NewPlayerJoined = <NewPlayerJoined>message;
      let newPlayer: Player = new Player(newPlayerJoined.newPlayerUUID);
      newPlayer.id = newPlayerJoined.newPlayerID;
      _this.game.addPlayer(newPlayer);
    });

    AnswerUpdate.registerListener("AnswerUpdate", function (message: Message) {
      let answerUpdate: AnswerUpdate = <AnswerUpdate>message;
      let player: Player = _this.game.findPlayer(answerUpdate.playerUUID);
      let answer: Answer = new Answer(answerUpdate.answerUUID);
      answer.answerValue = answerUpdate.answerValue;
      player.latestAnswer = answer;
    });

    UpdateScore.registerListener("UpdateScore", function (message: Message) {
      let updateScore: UpdateScore = <UpdateScore>message;
      _this.game.updateScore(updateScore.playerUUID, updateScore.score);
    });
  }

  nextQuestion(): void {
    let newQuestion: NewQuestion = new NewQuestion();
    this.webSocketService.sendCallback(newQuestion, function (responseMessage) {
      console.log("Got back from new question");
    });
  }
}
