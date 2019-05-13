import {Player} from "./player";

interface HashMap {
  [key: string]: Player;
}

export class Game {
  private uuid: string;

  private _players: HashMap = {};
  private _playersArray = [];

  constructor(uuid: string) {
    this.uuid = uuid;
  }

  addPlayer(player: Player) {
    if (this._players[player.uuid] == undefined) {
      this._players[player.uuid] = player;
      this._playersArray.push(player);
    } else {
      console.log("Player already added");
    }
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
}