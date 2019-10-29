//THIS FILE IS AUTO GENERATED - DO NOT MANUALLY CHANGE
import {Message} from "./message";

export class CalculateSum extends Message {
	private _formula: string;
	private _result: number;
	
	constructor() {
		super();
		this.type = "CalculateSum";
	}

	decodeResponse(msgRaw: any) {
		this._result = msgRaw.result;
	}

	get formula(): string {
		return this._formula;
	}
	
	get result(): number {
		return this._result;
	}
	
	set formula(value: string) {
		this._formula = value;
	}
	
	set result(value: number) {
		this._result = value;
	}
	
}