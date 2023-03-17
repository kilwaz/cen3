import {EchoPush} from "../wsActions/echoPush";
import {HeartBeat} from "../wsActions/heartBeat";
import {FormulaCheck} from "../wsActions/formulaCheck";

export abstract class ClassDictionary {
  // Surely this can be auto generated?
  public static getClass(className: string): Object {
    switch (className) {
      case "EchoPush":
        return EchoPush;
      case "HeartBeat":
        return HeartBeat;
      case "FormulaCheck":
        return FormulaCheck;
    }
    return "";
  }
}

