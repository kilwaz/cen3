import {Component, OnInit} from '@angular/core';
import {WebSocketService} from "../services/websocket.service";
import {NewQuestion} from "../wsObjects/newQuestion";
import {ResetGame} from "../wsObjects/resetGame";
import {CountDownTrigger} from "../wsObjects/countDownTrigger";
import {Game} from "../game";
import {Player} from "../player";
import {GameMasterJoin} from "../wsObjects/gameMasterJoin";
import {PlayerNameUpdate} from "../wsObjects/playerNameUpdate";
import {Message} from "../wsObjects/message";
import {MarkAnswers} from "../wsObjects/markAnswers";
import {TriggerRoundEnd} from "../wsObjects/triggerRoundEnd";
import {ClearGameScreen} from "../wsObjects/clearGameScreen";
import {ClearScreen} from "../wsObjects/clearScreen";
import {DisplayMessage} from "../wsObjects/displayMessage";

@Component({
  selector: 'app-game-master',
  templateUrl: './game-master.component.html',
  styleUrls: ['./game-master.component.css']
})
export class GameMasterComponent implements OnInit {
  webSocketService: WebSocketService;
  webSocketServiceReference = WebSocketService;

  game: Game;

  countDownActive: boolean = false;
  private countDownRemaining: number = 0;
  private countDownTimer: number = 0;

  private messageText:string = "";

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
    let newQuestion: NewQuestion = new NewQuestion();
    this.webSocketService.sendCallback(newQuestion, function (responseMessage) {
    });
  }

  resetGame() {
    let resetGame = new ResetGame();
    let _this: GameMasterComponent = this;
    this.webSocketService.sendCallback(resetGame, function (responseMessage) {
    });
  }

  countDownTrigger() {
    let _this: GameMasterComponent = this;
    let countDownTrigger = new CountDownTrigger();
    this.webSocketService.sendCallback(countDownTrigger, function (responseMessage) {
      _this.countDownRemaining = 5;
      _this.countDownActive = true;
      _this.countDownTimer = setInterval(_this.tickTimer, 1000, _this);
    });
  }

  markAnswers() {
    let markAnswers = new MarkAnswers();
    this.webSocketService.sendCallback(markAnswers, function (responseMessage) {
    });
  }

  endRound() {
    let triggerRoundEnd = new TriggerRoundEnd();
    this.webSocketService.sendCallback(triggerRoundEnd, function (responseMessage) {
    });
  }

  clearScreen() {
    let clearScreen = new ClearScreen();
    this.webSocketService.sendCallback(clearScreen, function (responseMessage) {
    });
  }

  displayMessage() {
    let displayMessage = new DisplayMessage();
    displayMessage.message = this.messageText;
    this.webSocketService.sendCallback(displayMessage, function (responseMessage) {
    });
  }

  updateDisplayMessage(event){
    this.messageText = event.target.value;
  }

  tickTimer(_this: GameMasterComponent) {
    if (_this.countDownRemaining == 0) {
      _this.countDownActive = false;
      clearInterval(_this.countDownTimer);
    } else {
      _this.countDownRemaining = _this.countDownRemaining - 1;
    }
  }
}
