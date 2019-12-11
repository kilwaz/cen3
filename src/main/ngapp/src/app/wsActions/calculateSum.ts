/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataobjects.CalculateSumData
DO NOT MANUALLY CHANGE THIS FILE
*/

import {Message} from "./message";
import {Formula} from "../wsObjects/formula";

export class CalculateSum extends Message {
	private _formulaToProcess: string;
	private _result: number;
	private _formula: Formula;
	
	constructor() {
		super();
		this.type = "CalculateSum";
	}

	decodeResponse(msgRaw: any) {
		this._result = msgRaw.result;
		this._formula = msgRaw.formula;
	}

	get formulaToProcess(): string {
		return this._formulaToProcess;
	}
	
	get result(): number {
		return this._result;
	}
	
	get formula(): Formula {
		return this._formula;
	}
	
	set formulaToProcess(value: string) {
		this._formulaToProcess = value;
	}
	
	set result(value: number) {
		this._result = value;
	}
	
	set formula(value: Formula) {
		this._formula = value;
	}
	
}