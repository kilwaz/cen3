//THIS FILE IS AUTO GENERATED - DO NOT MANUALLY CHANGE
import {Message} from "./message";

export class EchoPush extends Message {
	private _echoPushedMessage: string;
	
	constructor() {
		super();
		this.type = "EchoPush";
	}

	decodeResponse(msgRaw: any) {
		this._echoPushedMessage = msgRaw.echoPushedMessage;
	}

	get echoPushedMessage(): string {
		return this._echoPushedMessage;
	}
	
	set echoPushedMessage(value: string) {
		this._echoPushedMessage = value;
	}
	
}