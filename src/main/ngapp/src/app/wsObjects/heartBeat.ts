import {Message} from "./message";

export class HeartBeat extends Message {
	private _hb: boolean;
	
	constructor() {
		super();
		this.type = "HeartBeat";
	}

	decodeResponse(msgRaw: any) {
		this._hb = msgRaw.hb;
	}

	get hb(): boolean {
		return this._hb;
	}
	
	set hb(value: boolean) {
		this._hb = value;
	}
	
}