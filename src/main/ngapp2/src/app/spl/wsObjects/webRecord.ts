/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataitems.WebRecord
DO NOT MANUALLY CHANGE THIS FILE
*/

import {WebEntry} from "./webEntry";
import {WebProperty} from "./webProperty";

export class WebRecord {
	private _uuid: string;
	private _entries: Array<WebEntry> = [];
	private _properties: Array<WebProperty> = [];
	
	constructor() {
	}
	
	wsFill(webSocketReference: any) : WebRecord {
		this._uuid = webSocketReference.uuid != undefined ? webSocketReference.uuid : this._uuid;
		this._entries = webSocketReference.entries != undefined ? webSocketReference.entries : this._entries;
		this._properties = webSocketReference.properties != undefined ? webSocketReference.properties : this._properties;
		return this;
	}

	get uuid(): string {
		return this._uuid;
	}
	
	get entries(): Array<WebEntry> {
		return this._entries;
	}
	
	get properties(): Array<WebProperty> {
		return this._properties;
	}
	
	set uuid(value: string) {
		this._uuid = value;
	}
	
	set entries(value: Array<WebEntry>) {
		this._entries = value;
	}
	
	set properties(value: Array<WebProperty>) {
		this._properties = value;
	}
	
}