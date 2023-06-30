/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataobjects.RequestDefinitionsData
DO NOT MANUALLY CHANGE THIS FILE
*/

import {Message} from "./message";
import {DefinitionDataItem} from "../wsObjects/definitionDataItem";

export class RequestDefinitions extends Message {
	private _requestedRecordDefinitionName: string;
	private _definitions: Array<DefinitionDataItem> = [];
	
	constructor() {
		super();
		this.type = "RequestDefinitions";
	}

	decodeResponse(msgRaw: any) {
		this._definitions = msgRaw.definitions;
	}

	get requestedRecordDefinitionName(): string {
		return this._requestedRecordDefinitionName;
	}
	
	get definitions(): Array<DefinitionDataItem> {
		return this._definitions;
	}
	
	set requestedRecordDefinitionName(value: string) {
		this._requestedRecordDefinitionName = value;
	}
	
	set definitions(value: Array<DefinitionDataItem>) {
		this._definitions = value;
	}
	
}