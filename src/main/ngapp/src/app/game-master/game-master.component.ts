import {Component, OnInit} from '@angular/core';
import {WebSocketService} from "../websocket.service";
import {NewQuestion} from "../wsObjects/newQuestion";
import {ResetGame} from "../wsObjects/resetGame";
import {CountDownTrigger} from "../wsObjects/countDownTrigger";
import {Game} from "../game";
import {Player} from "../player";
import {GameMasterJoin} from "../wsObjects/gameMasterJoin";
import {PlayerNameUpdate} from "../wsObjects/playerNameUpdate";
import {Message} from "../wsObjects/message";

@Component({
  selector: 'app-game-master',
  templateUrl: './game-master.component.html',
  styleUrls: ['./game-master.component.css']
})
export class GameMasterComponent implements OnInit {
  webSocketService: WebSocketService;
  webSocketServiceReference = WebSocketService;

  private game: Game;

  constructor(private webSocketServiceConst: WebSocketService) {
    this.webSocketService = webSocketServiceConst;
  }

  ngOnInit() {
    let _this: GameMasterComponent = this;

    let gameMasterJoin = new GameMasterJoin();

    this.webSocketService.sendCallback(gameMasterJoin, function (responseMessage) {
      _this.game = new Game(gameMasterJoin.gameMasterUUID);
      window.localStorage.setItem("gameMasterUUID", gameMasterJoin.gameMasterUUID);

      for (let index in responseMessage.players) {
        let playerInfo = responseMessage.players[index];
        let player: Player = new Player(playerInfo.playerUUID);
        player.id = playerInfo.playerID;
        player.name = playerInfo.playerName;
        _this.game.addPlayer(player);
      }
    });

    PlayerNameUpdate.registerListener("PlayerNameUpdate", function (message: Message) {
      let playerNameUpdate: PlayerNameUpdate = <PlayerNameUpdate>message;
      _this.game.findPlayer(playerNameUpdate.playerUUID).name = playerNameUpdate.newName;
    });
  }

  nextQuestion(): void {
    let _this: GameMasterComponent = this;
    let newQuestion: NewQuestion = new NewQuestion();
    // this.game.clearLatestAnswers();
    this.webSocketService.sendCallback(newQuestion, function (responseMessage) {
      let responseNewQuestion: NewQuestion = <NewQuestion>responseMessage;
      // _this.questionText = responseNewQuestion.questionText
    });
  }

  resetGame() {
    let resetGame = new ResetGame();
    let _this: GameMasterComponent = this;
    this.webSocketService.sendCallback(resetGame, function (responseMessage) {
      // _this.questionText = undefined;
    });
  }

  countDownTrigger() {
    let countDownTrigger = new CountDownTrigger();
    this.webSocketService.sendCallback(countDownTrigger, function (responseMessage) {

    });
  }
}
