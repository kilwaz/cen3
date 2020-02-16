/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataobjects.DataQueryData
DO NOT MANUALLY CHANGE THIS FILE
*/

import {Message} from "./message";
import {Entry} from "../wsObjects/entry";

export class DataQuery extends Message {
	private _requestedEntries: Array<Entry> = [];
	private _recordToCheck: string;
	private _entries: Array<Entry> = [];
	
	constructor() {
		super();
		this.type = "DataQuery";
	}

	decodeResponse(msgRaw: any) {
		this._entries = msgRaw.entries;
	}

	get requestedEntries(): Array<Entry> {
		return this._requestedEntries;
	}
	
	get recordToCheck(): string {
		return this._recordToCheck;
	}
	
	get entries(): Array<Entry> {
		return this._entries;
	}
	
	set requestedEntries(value: Array<Entry>) {
		this._requestedEntries = value;
	}
	
	set recordToCheck(value: string) {
		this._recordToCheck = value;
	}
	
	set entries(value: Array<Entry>) {
		this._entries = value;
	}
	
}