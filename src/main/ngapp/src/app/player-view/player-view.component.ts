import {Component, OnInit} from '@angular/core';
import {WebSocketService} from "../websocket.service";
import {JoinGame} from "../wsObjects/joinGame";
import {AnswerResponse} from "../wsObjects/answerResponse";
import {Player} from "../player";
import {Message} from "../wsObjects/message";
import {NextQuestion} from "../wsObjects/nextQuestion";
import {Question} from "../question";
import {QuestionOption} from "../questionOption";
import {PlayerNameChange} from "../wsObjects/playerNameChange";
import {StartCountDown} from "../wsObjects/startCountDown";

@Component({
  selector: 'app-player-view',
  templateUrl: './player-view.component.html',
  styleUrls: ['./player-view.component.css']
})
export class PlayerViewComponent implements OnInit {
  webSocketService: WebSocketService;
  webSocketServiceReference = WebSocketService;

  private player: Player;
  private currentQuestion: Question;

  private noQuestionsYet: boolean = true;
  private changingName: boolean = true;

  private newPlayerName: string = "";

  private countDownActive: boolean = false;
  private countDownRemaining: number = 0;
  private countDownTimer;

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
      player.name = joinGame.playerName;
      window.localStorage.setItem("playerUUID", player.uuid);
      _this.player = player;
    });

    NextQuestion.registerListener("NextQuestion", function (message: Message) {
      let nextQuestion: NextQuestion = <NextQuestion>message;
      let question: Question = new Question(nextQuestion.questionUUID);
      _this.noQuestionsYet = false;

      for (let index in nextQuestion.questionOptions) {
        let option = nextQuestion.questionOptions[index];
        let questionOption: QuestionOption = new QuestionOption(option.optionUUID);
        questionOption.optionAnswer = option.optionAnswer;
        question.addQuestionOption(questionOption);
      }

      _this.currentQuestion = question;
    });

    StartCountDown.registerListener("StartCountDown", function (message: Message) {
      let startCountDown: StartCountDown = <StartCountDown>message;
      _this.countDownActive = true;
      _this.countDownRemaining = startCountDown.countDownSeconds;
      _this.countDownTimer = setInterval(_this.tickTimer, 1000, _this);
    });
  }

  playerButtonPressed(button): void {
    let answer: AnswerResponse = new AnswerResponse();
    answer.answerValue = button;
    answer.playerUUID = this.player.uuid;
    this.webSocketService.sendCallback(answer, function (responseMessage) {
    });
  }

  playerNameChanged() {
    this.changingName = false;

    this.player.name = this.newPlayerName;

    let playerNameChange: PlayerNameChange = new PlayerNameChange();
    playerNameChange.playerUUID = this.player.uuid;
    playerNameChange.playerName = this.player.name;
    this.webSocketService.sendCallback(playerNameChange, function (responseMessage) {
    });
  }

  updateName(event) {
    this.newPlayerName = event.target.value;
  }

  enableChangeName() {
    this.changingName = true;
  }

  skipChangeName() {
    this.changingName = false;
  }

  tickTimer(_this: PlayerViewComponent) {
    if (_this.countDownRemaining == 0) {
      _this.countDownActive = false;
      clearInterval(_this.countDownTimer);
    } else {
      _this.countDownRemaining = _this.countDownRemaining - 1;
    }
  }
}
