/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataitems.WebRecord
DO NOT MANUALLY CHANGE THIS FILE
*/

import {WebEntry} from "./webEntry";

export class WebRecord {
	private _uuid: string;
	private _entries: Array<WebEntry> = [];
	
	constructor() {
	}
	
	wsFill(webSocketReference: any) : WebRecord {
		this._uuid = webSocketReference.uuid != undefined ? webSocketReference.uuid : this._uuid;
		this._entries = webSocketReference.entries != undefined ? webSocketReference.entries : this._entries;
		return this;
	}

	get uuid(): string {
		return this._uuid;
	}
	
	get entries(): Array<WebEntry> {
		return this._entries;
	}
	
	set uuid(value: string) {
		this._uuid = value;
	}
	
	set entries(value: Array<WebEntry>) {
		this._entries = value;
	}
	
}