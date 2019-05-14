import {Component, OnInit} from '@angular/core';
import {WebSocketService} from "../websocket.service";
import {JoinGame} from "../wsObjects/joinGame";
import {AnswerResponse} from "../wsObjects/answerResponse";
import {Player} from "../player";
import {Message} from "../wsObjects/message";
import {NextQuestion} from "../wsObjects/nextQuestion";
import {Question} from "../question";
import {QuestionOption} from "../questionOption";

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
