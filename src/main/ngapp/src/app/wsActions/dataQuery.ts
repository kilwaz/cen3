/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataobjects.DataQueryData
DO NOT MANUALLY CHANGE THIS FILE
*/

import {Message} from "./message";

export class DataQuery extends Message {
	private _formulaToCheck: string;
	private _result: string;
	
	constructor() {
		super();
		this.type = "DataQuery";
	}

	decodeResponse(msgRaw: any) {
		this._result = msgRaw.result;
	}

	get formulaToCheck(): string {
		return this._formulaToCheck;
	}
	
	get result(): string {
		return this._result;
	}
	
	set formulaToCheck(value: string) {
		this._formulaToCheck = value;
	}
	
	set result(value: string) {
		this._result = value;
	}
	
}