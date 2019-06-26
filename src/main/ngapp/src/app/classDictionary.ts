import {NewPlayerJoined} from "./wsObjects/newPlayerJoined";
import {AnswerUpdate} from "./wsObjects/answerUpdate";
import {NextQuestion} from "./wsObjects/nextQuestion";
import {UpdateScore} from "./wsObjects/updateScore";
import {PlayerNameUpdate} from "./wsObjects/playerNameUpdate";
import {StartCountDown} from "./wsObjects/startCountDown";
import {QuestionResults} from "./wsObjects/questionResults";
import {ClearGameScreen} from "./wsObjects/clearGameScreen";
import {DisplayGameMessage} from "./wsObjects/displayGameMessage";

export abstract class ClassDictionary {
  public static getClass(className: string): Object {
    switch (className) {
      case "NewPlayerJoined":
        return NewPlayerJoined;
      case "AnswerUpdate":
        return AnswerUpdate;
      case "NextQuestion":
        return NextQuestion;
      case "UpdateScore":
        return UpdateScore;
      case "PlayerNameUpdate":
        return PlayerNameUpdate;
      case "StartCountDown":
        return StartCountDown;
      case "QuestionResults":
        return QuestionResults;
      case "ClearGameScreen":
        return ClearGameScreen;
      case "DisplayGameMessage":
        return DisplayGameMessage;
    }
    return;
  }
}

