//THIS FILE IS AUTO GENERATED - DO NOT MANUALLY CHANGE
import {Message} from "./message";

export class ChatMessage extends Message {
	private _message: string;
	
	constructor() {
		super();
		this.type = "ChatMessage";
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