/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataitems.WebRecordDataItem
DO NOT MANUALLY CHANGE THIS FILE
*/

import {WebEntryDataItem} from "./webEntryDataItem";
import {WebPropertyDataItem} from "./webPropertyDataItem";

export class WebRecordDataItem {
	private _uuid: string;
	private _entries: Array<WebEntryDataItem> = [];
	private _properties: Array<WebPropertyDataItem> = [];
	
	constructor() {
	}
	
	wsFill(webSocketReference: any) : WebRecordDataItem {
		this._uuid = webSocketReference.uuid != undefined ? webSocketReference.uuid : this._uuid;
		this._entries = webSocketReference.entries != undefined ? webSocketReference.entries : this._entries;
		this._properties = webSocketReference.properties != undefined ? webSocketReference.properties : this._properties;
		return this;
	}

	get uuid(): string {
		return this._uuid;
	}
	
	get entries(): Array<WebEntryDataItem> {
		return this._entries;
	}
	
	get properties(): Array<WebPropertyDataItem> {
		return this._properties;
	}
	
	set uuid(value: string) {
		this._uuid = value;
	}
	
	set entries(value: Array<WebEntryDataItem>) {
		this._entries = value;
	}
	
	set properties(value: Array<WebPropertyDataItem>) {
		this._properties = value;
	}
	
}