import {NewPlayerJoined} from "./wsActions/newPlayerJoined";
import {AnswerUpdate} from "./wsActions/answerUpdate";
import {NextQuestion} from "./wsActions/nextQuestion";
import {UpdateScore} from "./wsActions/updateScore";
import {PlayerNameUpdate} from "./wsActions/playerNameUpdate";
import {StartCountDown} from "./wsActions/startCountDown";
import {QuestionResults} from "./wsActions/questionResults";
import {ClearGameScreen} from "./wsActions/clearGameScreen";
import {DisplayGameMessage} from "./wsActions/displayGameMessage";

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

