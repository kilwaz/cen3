import {Player} from "../wsObjects/player";
import {Game} from "./game";

interface HashMap {
  [key: string]: Player;
}

export class Scoreboard {
  private _game: Game;
  private _scores: HashMap = {};

  constructor() {

  }
}
