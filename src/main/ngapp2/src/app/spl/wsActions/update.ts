/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataobjects.UpdateData
DO NOT MANUALLY CHANGE THIS FILE
*/

import {Message} from "./message";
import {WebEntryDataItem} from "../wsObjects/webEntryDataItem";
import {WebRecordDataItem} from "../wsObjects/webRecordDataItem";

export class Update extends Message {
	private _value: string;
	private _definitionName: string;
	private _recordUUID: string;
	private _updateSource: string;
	private _entries: Array<WebEntryDataItem> = [];
	private _webRecord: WebRecordDataItem;
	
	constructor() {
		super();
		this.type = "Update";
	}

	decodeResponse(msgRaw: any) {
		this._recordUUID = msgRaw.recordUUID;
		this._entries = msgRaw.entries;
		this._webRecord = msgRaw.webRecord;
	}

	get value(): string {
		return this._value;
	}
	
	get definitionName(): string {
		return this._definitionName;
	}
	
	get recordUUID(): string {
		return this._recordUUID;
	}
	
	get updateSource(): string {
		return this._updateSource;
	}
	
	get entries(): Array<WebEntryDataItem> {
		return this._entries;
	}
	
	get webRecord(): WebRecordDataItem {
		return this._webRecord;
	}
	
	set value(value: string) {
		this._value = value;
	}
	
	set definitionName(value: string) {
		this._definitionName = value;
	}
	
	set recordUUID(value: string) {
		this._recordUUID = value;
	}
	
	set updateSource(value: string) {
		this._updateSource = value;
	}
	
	set entries(value: Array<WebEntryDataItem>) {
		this._entries = value;
	}
	
	set webRecord(value: WebRecordDataItem) {
		this._webRecord = value;
	}
	
}