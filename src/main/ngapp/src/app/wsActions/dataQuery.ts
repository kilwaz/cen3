/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataobjects.DataQueryData
DO NOT MANUALLY CHANGE THIS FILE
*/

import {Message} from "./message";
import {Entry} from "../wsObjects/entry";

export class DataQuery extends Message {
	private _recordToCheck: string;
	private _entry: Entry;
	
	constructor() {
		super();
		this.type = "DataQuery";
	}

	decodeResponse(msgRaw: any) {
		this._entry = msgRaw.entry;
	}

	get recordToCheck(): string {
		return this._recordToCheck;
	}
	
	get entry(): Entry {
		return this._entry;
	}
	
	set recordToCheck(value: string) {
		this._recordToCheck = value;
	}
	
	set entry(value: Entry) {
		this._entry = value;
	}
	
}