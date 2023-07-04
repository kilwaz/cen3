/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataobjects.DataQueryData
DO NOT MANUALLY CHANGE THIS FILE
*/

import {Message} from "./message";
import {WebEntryDataItem} from "../wsObjects/webEntryDataItem";

export class DataQuery extends Message {
	private _requestedEntries: Array<WebEntryDataItem> = [];
	private _recordToCheck: string;
	private _entries: Array<WebEntryDataItem> = [];
	
	constructor() {
		super();
		this.type = "DataQuery";
	}

	decodeResponse(msgRaw: any) {
		this._entries = msgRaw.entries;
	}

	get requestedEntries(): Array<WebEntryDataItem> {
		return this._requestedEntries;
	}
	
	get recordToCheck(): string {
		return this._recordToCheck;
	}
	
	get entries(): Array<WebEntryDataItem> {
		return this._entries;
	}
	
	set requestedEntries(value: Array<WebEntryDataItem>) {
		this._requestedEntries = value;
	}
	
	set recordToCheck(value: string) {
		this._recordToCheck = value;
	}
	
	set entries(value: Array<WebEntryDataItem>) {
		this._entries = value;
	}
	
}