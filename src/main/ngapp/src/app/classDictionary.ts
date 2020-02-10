import {EchoPush} from "./wsActions/echoPush";
import {HeartBeat} from "./wsActions/heartBeat";
import {DefinitionUpdate} from "./wsActions/definitionUpdate";
import {FormulaCheck} from "./wsActions/formulaCheck";

export abstract class ClassDictionary {
  // Surely this can be auto generated?
  public static getClass(className: string): Object {
    switch (className) {
      case "EchoPush":
        return EchoPush;
      case "DefinitionUpdate":
        return DefinitionUpdate;
      case "HeartBeat":
        return HeartBeat;
      case "FormulaCheck":
        return FormulaCheck;
    }
    return "";
  }
}

