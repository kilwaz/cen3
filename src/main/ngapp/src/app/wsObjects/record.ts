/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
game.actors.Record
DO NOT MANUALLY CHANGE THIS FILE
*/

import {Entry} from "./entry";

export class Record {
	private _uuid: string;
	private _entries: Array<Entry> = [];
	
	constructor() {
	}
	
	wsFill(webSocketReference: any) : Record {
		this._uuid = webSocketReference.uuid != undefined ? webSocketReference.uuid : this._uuid;
		this._entries = webSocketReference.entries != undefined ? webSocketReference.entries : this._entries;
		return this;
	}

	get uuid(): string {
		return this._uuid;
	}
	
	get entries(): Array<Entry> {
		return this._entries;
	}
	
	set uuid(value: string) {
		this._uuid = value;
	}
	
	set entries(value: Array<Entry>) {
		this._entries = value;
	}
	
}