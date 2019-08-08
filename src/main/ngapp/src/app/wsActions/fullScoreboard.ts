//THIS FILE IS AUTO GENERATED - DO NOT MANUALLY CHANGE
import {Message} from "./message";
import {ScoreUpdate} from "../scoreUpdate";

export class FullScoreboard extends Message {
	private _scores: Array<ScoreUpdate> = [];
	
	constructor() {
		super();
		this.type = "FullScoreboard";
	}

	decodeResponse(msgRaw: any) {
		this._scores = msgRaw.scores;
	}

	get scores(): Array<ScoreUpdate> {
		return this._scores;
	}
	
	set scores(value: Array<ScoreUpdate>) {
		this._scores = value;
	}
	
}