//THIS FILE IS AUTO GENERATED - DO NOT MANUALLY CHANGE
import {Message} from "./message";
import {QuestionOption} from "../questionOption";
import {PlayerResult} from "../playerResult";

export class QuestionResults extends Message {
	private _questionUUID: string;
	private _questionOptions: Array<QuestionOption> = [];
	private _playerResults: Array<PlayerResult> = [];
	private _correctOptionUUID: string;
	
	constructor() {
		super();
		this.type = "QuestionResults";
	}

	decodeResponse(msgRaw: any) {
		this._questionUUID = msgRaw.questionUUID;
		this._questionOptions = msgRaw.questionOptions;
		this._playerResults = msgRaw.playerResults;
		this._correctOptionUUID = msgRaw.correctOptionUUID;
	}

	get questionUUID(): string {
		return this._questionUUID;
	}
	
	get questionOptions(): Array<QuestionOption> {
		return this._questionOptions;
	}
	
	get playerResults(): Array<PlayerResult> {
		return this._playerResults;
	}
	
	get correctOptionUUID(): string {
		return this._correctOptionUUID;
	}
	
	set questionUUID(value: string) {
		this._questionUUID = value;
	}
	
	set questionOptions(value: Array<QuestionOption>) {
		this._questionOptions = value;
	}
	
	set playerResults(value: Array<PlayerResult>) {
		this._playerResults = value;
	}
	
	set correctOptionUUID(value: string) {
		this._correctOptionUUID = value;
	}
	
}