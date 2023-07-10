/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataobjects.RequestFormulaContextsData
DO NOT MANUALLY CHANGE THIS FILE
*/

import {Message} from "./message";
import {FormulaContextDataItem} from "../wsObjects/formulaContextDataItem";

export class RequestFormulaContexts extends Message {
	private _formulaContexts: Array<FormulaContextDataItem> = [];
	
	constructor() {
		super();
		this.type = "RequestFormulaContexts";
	}

	decodeResponse(msgRaw: any) {
		this._formulaContexts = msgRaw.formulaContexts;
	}

	get formulaContexts(): Array<FormulaContextDataItem> {
		return this._formulaContexts;
	}
	
	set formulaContexts(value: Array<FormulaContextDataItem>) {
		this._formulaContexts = value;
	}
	
}