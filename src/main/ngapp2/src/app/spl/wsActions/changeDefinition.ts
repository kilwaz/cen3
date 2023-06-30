/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataobjects.ChangeDefinitionData
DO NOT MANUALLY CHANGE THIS FILE
*/

import {Message} from "./message";
import {DefinitionDataItem} from "../wsObjects/definitionDataItem";

export class ChangeDefinition extends Message {
	private _definition: DefinitionDataItem;
	
	constructor() {
		super();
		this.type = "ChangeDefinition";
	}

	decodeResponse(msgRaw: any) {
		this._definition = msgRaw.definition;
	}

	get definition(): DefinitionDataItem {
		return this._definition;
	}
	
	set definition(value: DefinitionDataItem) {
		this._definition = value;
	}
	
}