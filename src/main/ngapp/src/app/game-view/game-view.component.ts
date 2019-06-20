import {Component, OnInit} from '@angular/core';
import {WebSocketService} from "../services/websocket.service";
import {AdminGame} from "../wsObjects/adminGame";
import {NewPlayerJoined} from "../wsObjects/newPlayerJoined";
import {Message} from "../wsObjects/message";
import {Game} from "../game";
import {Player} from "../player";
import {AnswerUpdate} from "../wsObjects/answerUpdate";
import {Answer} from "../answer";
import {UpdateScore} from "../wsObjects/updateScore";
import {PlayerNameUpdate} from "../wsObjects/playerNameUpdate";
import {NextQuestion} from "../wsObjects/nextQuestion";
import {Question} from "../question";
import {QuestionOption} from "../questionOption";
import {QuestionResults} from "../wsObjects/questionResults";
import {ClearGameScreen} from "../wsObjects/clearGameScreen";
import {DisplayGameMessage} from "../wsObjects/displayGameMessage";

@Component({
  selector: 'app-game-view',
  templateUrl: './game-view.component.html',
  styleUrls: ['./game-view.component.css']
})
export class GameViewComponent implements OnInit {
  webSocketService: WebSocketService;
  webSocketServiceReference = WebSocketService;

  game: Game;

  private currentQuestion: Question;

  private clearedScreen:boolean = true;
  private gameMessage:string = '';

  constructor(private webSocketServiceConst: WebSocketService) {
    this.webSocketService = webSocketServiceConst;
  }

  ngOnInit(): void {
    let _this: GameViewComponent = this;

    let adminGame = new AdminGame();
    adminGame.localStorageUUID = window.localStorage.getItem("adminUUID");

    this.webSocketService.sendCallback(adminGame, function (responseMessage) {
      _this.game = new Game(adminGame.adminUUID);
      window.localStorage.setItem("adminUUID", adminGame.adminUUID);

      for (let index in responseMessage.players) {
        let playerInfo = responseMessage.players[index];
        let player: Player = new Player(playerInfo.playerUUID);
        player.id = playerInfo.playerID;
        player.name = playerInfo.playerName;
        _this.game.addPlayer(player);
      }
    });

    NewPlayerJoined.registerListener("NewPlayerJoined", function (message: Message) {
      let newPlayerJoined: NewPlayerJoined = <NewPlayerJoined>message;
      let newPlayer: Player = new Player(newPlayerJoined.newPlayerUUID);
      newPlayer.id = newPlayerJoined.newPlayerID;
      newPlayer.name = newPlayerJoined.newPlayerName;
      _this.game.addPlayer(newPlayer);
    });

    AnswerUpdate.registerListener("AnswerUpdate", function (message: Message) {
      let answerUpdate: AnswerUpdate = <AnswerUpdate>message;
      let player: Player = _this.game.findPlayer(answerUpdate.playerUUID);
      let answer: Answer = new Answer(answerUpdate.answerUUID);
      answer.answerValue = answerUpdate.answerValue;
      player.latestAnswer = answer;
      player.playerStatus = "alert-primary";
    });

    UpdateScore.registerListener("UpdateScore", function (message: Message) {
      let updateScore: UpdateScore = <UpdateScore>message;

      for (let index in updateScore.scores) {
        let score = updateScore.scores[index];
        _this.game.findPlayer(score.playerUUID).score = score.score;
      }
      _this.game.sortPlayersByScore();
    });

    ClearGameScreen.registerListener("ClearGameScreen", function (message: Message) {
      let clearGameScreen: ClearGameScreen = <ClearGameScreen>message;
      _this.gameMessage = "";
      _this.clearedScreen = true;
    });

    DisplayGameMessage.registerListener("DisplayGameMessage", function (message: Message) {
      let displayGameMessage: DisplayGameMessage = <DisplayGameMessage>message;
      _this.gameMessage = displayGameMessage.message;
      _this.clearedScreen = true;
    });

    NextQuestion.registerListener("NextQuestion", function (message: Message) {
      let nextQuestion: NextQuestion = <NextQuestion>message;
      for (let index in _this.game.players) {
        _this.game.players[index].latestAnswer = undefined;
        _this.game.players[index].playerStatus = "alert-light";
      }

      let question: Question = new Question(nextQuestion.questionUUID);
      for (let index in nextQuestion.questionOptions) {
        let option = nextQuestion.questionOptions[index];
        let questionOption: QuestionOption = new QuestionOption(option.optionUUID);
        questionOption.answerProgressClass = "alert-primary";
        questionOption.optionAnswer = option.optionAnswer;
        question.addQuestionOption(questionOption);
      }

      question.questionText = nextQuestion.questionText;
      _this.currentQuestion = question;
      _this.clearedScreen = false;
    });

    QuestionResults.registerListener("QuestionResults", function (message: Message) {
      let questionResults: QuestionResults = <QuestionResults>message;
      let totalAnswers: number = 0;

      for (let index in questionResults.playerResults) {
        let playerResult = questionResults.playerResults[index];
        let player: Player = _this.game.findPlayer(playerResult.playerUUID);
        if (player) {
          if (playerResult.isCorrectAnswer) {
            player.playerStatus = "alert-success";
          } else {
            player.playerStatus = "alert-danger";
          }
        }
      }

      for (let index in questionResults.questionOptions) {
        let option = questionResults.questionOptions[index];
        totalAnswers = totalAnswers + option.optionAnswerCount;
      }

      for (let index in questionResults.questionOptions) {
        let option = questionResults.questionOptions[index];
        let foundOption = _this.currentQuestion.findQuestionOption(option.optionUUID);
        if (foundOption) {
          foundOption.answerProgressClass = (option.isCorrectAnswer ? "alert-success" : "alert-danger");
          foundOption.optionAnswerCount = option.optionAnswerCount;
          foundOption.optionPercentageOfTotalAnswers = (option.optionAnswerCount / totalAnswers) * 100;
        }
      }

      _this.currentQuestion.totalAnswers = totalAnswers;
    });

    PlayerNameUpdate.registerListener("PlayerNameUpdate", function (message: Message) {
      let playerNameUpdate: PlayerNameUpdate = <PlayerNameUpdate>message;
      _this.game.findPlayer(playerNameUpdate.playerUUID).name = playerNameUpdate.newName;
    });
  }
}
