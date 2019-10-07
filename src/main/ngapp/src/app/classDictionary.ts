import {EchoPush} from "./wsActions/echoPush";
import {HeartBeat} from "./wsActions/heartBeat";

export abstract class ClassDictionary {
  public static getClass(className: string): Object {
    switch (className) {
      case "EchoPush":
        return EchoPush;
      case "HeartBeat":
        return HeartBeat;
    }
    return "";
  }
}

