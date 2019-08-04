import {Message} from "./message";

export class ClearGameScreen extends Message {
	private _clear: boolean;
	
	constructor() {
		super();
		this.type = "ClearGameScreen";
	}

	decodeResponse(msgRaw: any) {
		this._clear = msgRaw.clear;
	}

	get clear(): boolean {
		return this._clear;
	}
	
	set clear(value: boolean) {
		this._clear = value;
	}
	
}