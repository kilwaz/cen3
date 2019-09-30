//THIS FILE IS AUTO GENERATED - DO NOT MANUALLY CHANGE
import {Message} from "./message";

export class AllPlayers extends Message {
	private _message: string;
	
	constructor() {
		super();
		this.type = "AllPlayers";
	}

	decodeResponse(msgRaw: any) {
	}

	get message(): string {
		return this._message;
	}
	
	set message(value: string) {
		this._message = value;
	}
	
}