//THIS FILE IS AUTO GENERATED - DO NOT MANUALLY CHANGE
import {Message} from "./message";

export class StartCountDown extends Message {
	private _countDownSeconds: number;
	
	constructor() {
		super();
		this.type = "StartCountDown";
	}

	decodeResponse(msgRaw: any) {
		this._countDownSeconds = msgRaw.countDownSeconds;
	}

	get countDownSeconds(): number {
		return this._countDownSeconds;
	}
	
	set countDownSeconds(value: number) {
		this._countDownSeconds = value;
	}
	
}