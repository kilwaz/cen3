//THIS FILE IS AUTO GENERATED - DO NOT MANUALLY CHANGE
import {Player} from "./player";

export class Score {
	private _score: number;
	private _player: Player;
	
	constructor() {
	}

	get score(): number {
		return this._score;
	}
	
	get player(): Player {
		return this._player;
	}
	
	set score(value: number) {
		this._score = value;
	}
	
	set player(value: Player) {
		this._player = value;
	}
	
}