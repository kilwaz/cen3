/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataobjects.DefinitionLookupData
DO NOT MANUALLY CHANGE THIS FILE
*/

import {Message} from "./message";
import {Definition} from "../wsObjects/definition";

export class DefinitionLookup extends Message {
	private _entries: Array<Definition> = [];
	
	constructor() {
		super();
		this.type = "DefinitionLookup";
	}

	decodeResponse(msgRaw: any) {
		this._entries = msgRaw.entries;
	}

	get entries(): Array<Definition> {
		return this._entries;
	}
	
	set entries(value: Array<Definition>) {
		this._entries = value;
	}
	
}