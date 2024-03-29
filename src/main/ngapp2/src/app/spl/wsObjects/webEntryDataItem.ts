/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataitems.WebEntryDataItem
DO NOT MANUALLY CHANGE THIS FILE
*/


export class WebEntryDataItem {
	private _value: string;
	private _recordUUID: string;
	private _name: string;
	
	constructor() {
	}
	
	wsFill(webSocketReference: any) : WebEntryDataItem {
		this._value = webSocketReference.value != undefined ? webSocketReference.value : this._value;
		this._recordUUID = webSocketReference.recordUUID != undefined ? webSocketReference.recordUUID : this._recordUUID;
		this._name = webSocketReference.name != undefined ? webSocketReference.name : this._name;
		return this;
	}

	get value(): string {
		return this._value;
	}
	
	get recordUUID(): string {
		return this._recordUUID;
	}
	
	get name(): string {
		return this._name;
	}
	
	set value(value: string) {
		this._value = value;
	}
	
	set recordUUID(value: string) {
		this._recordUUID = value;
	}
	
	set name(value: string) {
		this._name = value;
	}
	
}