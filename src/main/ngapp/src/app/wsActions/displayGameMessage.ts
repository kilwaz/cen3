//THIS FILE IS AUTO GENERATED - DO NOT MANUALLY CHANGE
import {Message} from "./message";

export class DisplayGameMessage extends Message {
	private _message: string;
	
	constructor() {
		super();
		this.type = "DisplayGameMessage";
	}

	decodeResponse(msgRaw: any) {
		this._message = msgRaw.message;
	}

	get message(): string {
		return this._message;
	}
	
	set message(value: string) {
		this._message = value;
	}
	
}