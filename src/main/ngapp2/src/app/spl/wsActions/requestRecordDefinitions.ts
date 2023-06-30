/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataobjects.RequestRecordDefinitionsData
DO NOT MANUALLY CHANGE THIS FILE
*/

import {Message} from "./message";
import {RecordDefinitionDataItem} from "../wsObjects/recordDefinitionDataItem";

export class RequestRecordDefinitions extends Message {
	private _recordDefinitions: Array<RecordDefinitionDataItem> = [];
	
	constructor() {
		super();
		this.type = "RequestRecordDefinitions";
	}

	decodeResponse(msgRaw: any) {
		this._recordDefinitions = msgRaw.recordDefinitions;
	}

	get recordDefinitions(): Array<RecordDefinitionDataItem> {
		return this._recordDefinitions;
	}
	
	set recordDefinitions(value: Array<RecordDefinitionDataItem>) {
		this._recordDefinitions = value;
	}
	
}