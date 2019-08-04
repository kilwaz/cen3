import {Message} from "./message";

export class AnswerResponse extends Message {
	private _answerValue: string;
	private _playerUUID: string;
	
	constructor() {
		super();
		this.type = "AnswerResponse";
	}

	decodeResponse(msgRaw: any) {
	}

	get answerValue(): string {
		return this._answerValue;
	}
	
	get playerUUID(): string {
		return this._playerUUID;
	}
	
	set answerValue(value: string) {
		this._answerValue = value;
	}
	
	set playerUUID(value: string) {
		this._playerUUID = value;
	}
	
}