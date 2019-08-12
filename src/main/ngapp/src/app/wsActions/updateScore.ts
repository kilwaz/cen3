//THIS FILE IS AUTO GENERATED - DO NOT MANUALLY CHANGE
import {Message} from "./message";
import {ScoreUpdate} from "../wsObjects/scoreUpdate";

export class UpdateScore extends Message {
	private _scores: Array<ScoreUpdate> = [];
	
	constructor() {
		super();
		this.type = "UpdateScore";
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