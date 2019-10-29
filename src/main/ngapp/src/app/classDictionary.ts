import {EchoPush} from "./wsActions/echoPush";
import {HeartBeat} from "./wsActions/heartBeat";
import {DefinitionUpdate} from "./wsActions/definitionUpdate";
import {CalculateSum} from "./wsActions/calculateSum";

export abstract class ClassDictionary {
  public static getClass(className: string): Object {
    switch (className) {
      case "EchoPush":
        return EchoPush;
      case "DefinitionUpdate":
        return DefinitionUpdate;
      case "CalculateSum":
        return CalculateSum;
      case "HeartBeat":
        return HeartBeat;
    }
    return "";
  }
}

