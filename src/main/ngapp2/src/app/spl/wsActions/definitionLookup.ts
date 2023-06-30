/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataobjects.DefinitionLookupData
DO NOT MANUALLY CHANGE THIS FILE
*/

import {Message} from "./message";
import {DefinitionDataItem} from "../wsObjects/definitionDataItem";

export class DefinitionLookup extends Message {
	private _name: string;
	private _entries: Array<DefinitionDataItem> = [];
	
	constructor() {
		super();
		this.type = "DefinitionLookup";
	}

	decodeResponse(msgRaw: any) {
		this._name = msgRaw.name;
		this._entries = msgRaw.entries;
	}

	get name(): string {
		return this._name;
	}
	
	get entries(): Array<DefinitionDataItem> {
		return this._entries;
	}
	
	set name(value: string) {
		this._name = value;
	}
	
	set entries(value: Array<DefinitionDataItem>) {
		this._entries = value;
	}
	
}