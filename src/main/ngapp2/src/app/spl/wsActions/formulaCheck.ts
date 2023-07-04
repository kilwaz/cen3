/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataobjects.FormulaCheckData
DO NOT MANUALLY CHANGE THIS FILE
*/

import {Message} from "./message";
import {FormulaDataItem} from "../wsObjects/formulaDataItem";

export class FormulaCheck extends Message {
	private _formulaToCheck: string;
	private _result: string;
	private _formula: FormulaDataItem;
	
	constructor() {
		super();
		this.type = "FormulaCheck";
	}

	decodeResponse(msgRaw: any) {
		this._result = msgRaw.result;
		this._formula = msgRaw.formula;
	}

	get formulaToCheck(): string {
		return this._formulaToCheck;
	}
	
	get result(): string {
		return this._result;
	}
	
	get formula(): FormulaDataItem {
		return this._formula;
	}
	
	set formulaToCheck(value: string) {
		this._formulaToCheck = value;
	}
	
	set result(value: string) {
		this._result = value;
	}
	
	set formula(value: FormulaDataItem) {
		this._formula = value;
	}
	
}