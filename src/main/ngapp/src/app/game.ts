import {Player} from "./player";
import {Score} from "./score";

interface HashMapPlayer {
  [key: string]: Player;
}

interface HashMapScore {
  [key: string]: Score;
}

export class Game {
  private uuid: string;

  private _players: HashMapPlayer = {};
  private _scores: HashMapScore = {};
  private _playersArray = [];

  constructor(uuid: string) {
    this.uuid = uuid;
  }

  addPlayer(player: Player) {
    if (this._players[player.uuid] == undefined) {
      this._players[player.uuid] = player;
      this._scores[player.uuid] = new Score(player);
      this._playersArray.push(player);
    } else {
      console.log("Player already added");
    }
  }

  updateScore(playerUUID: string, score: number) {
    this._scores[playerUUID].score = score;
  }

  removePlayer(player: Player) {
    delete this._players[player.uuid];
    // delete this._playersArray[player];
  }

  findPlayer(uuid: string) {
    return this._players[uuid];
  }

  get players(): any[] {
    return this._playersArray;
  }

  findScore(uuid: string) {
    return this._scores[uuid];
  }
}
