import {Component, OnInit} from '@angular/core';
import {WebSocketService} from "../services/websocket.service";
import {AdminGame} from "../wsActions/adminGame";
import {NewPlayerJoined} from "../wsActions/newPlayerJoined";
import {Message} from "../wsActions/message";
import {Game} from "../actors/game";
import {Player} from "../wsObjects/player";
import {AnswerUpdate} from "../wsActions/answerUpdate";
import {Answer} from "../wsObjects/answer";
import {UpdateScore} from "../wsActions/updateScore";
import {PlayerNameUpdate} from "../wsActions/playerNameUpdate";
import {NextQuestion} from "../wsActions/nextQuestion";
import {Question} from "../wsObjects/question";
import {QuestionOption} from "../wsObjects/questionOption";
import {QuestionResults} from "../wsActions/questionResults";
import {ClearGameScreen} from "../wsActions/clearGameScreen";
import {DisplayGameMessage} from "../wsActions/displayGameMessage";

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

  private clearedScreen: boolean = true;
  private gameMessage: string = '';

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
        let player: Player = new Player();
        player.uuid = playerInfo.playerUUID;
        player.id = playerInfo.playerID;
        player.name = playerInfo.playerName;
        _this.game.addPlayer(player);
      }
    });

    NewPlayerJoined.registerListener("NewPlayerJoined", function (message: Message) {
      let newPlayerJoined: NewPlayerJoined = <NewPlayerJoined>message;
      let newPlayer: Player = new Player();
      newPlayer.uuid = newPlayerJoined.newPlayerUUID;
      newPlayer.id = newPlayerJoined.newPlayerID;
      newPlayer.name = newPlayerJoined.newPlayerName;
      _this.game.addPlayer(newPlayer);
    });

    AnswerUpdate.registerListener("AnswerUpdate", function (message: Message) {
      let answerUpdate: AnswerUpdate = <AnswerUpdate>message;
      let player: Player = _this.game.findPlayer(answerUpdate.playerUUID);
      let answer: Answer = new Answer();
      answer.uuid = answerUpdate.answerUUID;
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

      let question: Question = new Question();
      question.uuid = nextQuestion.nextQuestionUUID;
      for (let index in nextQuestion.options) {
        let option = nextQuestion.options[index];
        let questionOption: QuestionOption = new QuestionOption();
        questionOption.uuid = option.uuid;
        // questionOption.answerProgressClass = "alert-primary";
        questionOption.answerValue = option.answerValue;
        question.possibleOptions.push(questionOption);
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
        totalAnswers = totalAnswers + option.countedAnswers;
      }

      for (let index in questionResults.questionOptions) {
        let option = questionResults.questionOptions[index];
        let possibleOptions = _this.currentQuestion.possibleOptions;
        let foundOption = undefined;
        for (let index in questionResults.questionOptions) {
          if (possibleOptions[index].uuid == option.uuid) {
            foundOption = questionResults.questionOptions[index];
            break;
          }
        }
        foundOption.uuid = option.uuid;
        if (foundOption) {
          // foundOption.answerProgressClass = (option.isCorrectAnswer ? "alert-success" : "alert-danger");
          foundOption.optionAnswerCount = option.countedAnswers;
          foundOption.optionPercentageOfTotalAnswers = (option.countedAnswers / totalAnswers) * 100;
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
