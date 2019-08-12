import {Score} from "../wsObjects/score";
import {Player} from "../wsObjects/player";

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
  private _playersArray: Player[] = [];

  constructor(uuid: string) {
    this.uuid = uuid;
  }

  addPlayer(player: Player) {
    if (this._players[player.uuid] == undefined) {
      this._players[player.uuid] = player;
      let score: Score = new Score();
      score.player = player;
      this._scores[player.uuid] = score;
      this._playersArray.push(player);
    } else {
      console.log("Player already added");
    }

    this.sortPlayersByScore();
  }

  sortPlayersByScore() {
    this._playersArray.sort((n2, n1) => {
      if (n1.score > n2.score) {
        return 1;
      }
      if (n1.score < n2.score) {
        return -1;
      }
      return 0;
    });
  }

  removePlayer(player: Player) {
    delete this._players[player.uuid];
    // delete this._playersArray[player];
  }

  findPlayer(uuid: string) {
    return this._players[uuid];
  }

  get players(): Player[] {
    return this._playersArray;
  }

  findScore(uuid: string) {
    return this._scores[uuid];
  }

  clearLatestAnswers() {
    for (let player of this._playersArray) {
      player.latestAnswer = undefined;
    }
  }
}
