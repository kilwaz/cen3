/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataobjects.DataQueryData
DO NOT MANUALLY CHANGE THIS FILE
*/

import {Message} from "./message";
import {WebEntry} from "../wsObjects/webEntry";

export class DataQuery extends Message {
	private _requestedEntries: Array<WebEntry> = [];
	private _recordToCheck: string;
	private _entries: Array<WebEntry> = [];
	
	constructor() {
		super();
		this.type = "DataQuery";
	}

	decodeResponse(msgRaw: any) {
		this._entries = msgRaw.entries;
	}

	get requestedEntries(): Array<WebEntry> {
		return this._requestedEntries;
	}
	
	get recordToCheck(): string {
		return this._recordToCheck;
	}
	
	get entries(): Array<WebEntry> {
		return this._entries;
	}
	
	set requestedEntries(value: Array<WebEntry>) {
		this._requestedEntries = value;
	}
	
	set recordToCheck(value: string) {
		this._recordToCheck = value;
	}
	
	set entries(value: Array<WebEntry>) {
		this._entries = value;
	}
	
}