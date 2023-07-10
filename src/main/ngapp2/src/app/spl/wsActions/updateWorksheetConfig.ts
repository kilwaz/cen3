/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataobjects.UpdateWorksheetConfigData
DO NOT MANUALLY CHANGE THIS FILE
*/

import {Message} from "./message";

export class UpdateWorksheetConfig extends Message {
	private _definitionId: string;
	private _worksheetConfigName: string;
	
	constructor() {
		super();
		this.type = "UpdateWorksheetConfig";
	}

	decodeResponse(msgRaw: any) {
	}

	get definitionId(): string {
		return this._definitionId;
	}
	
	get worksheetConfigName(): string {
		return this._worksheetConfigName;
	}
	
	set definitionId(value: string) {
		this._definitionId = value;
	}
	
	set worksheetConfigName(value: string) {
		this._worksheetConfigName = value;
	}
	
}