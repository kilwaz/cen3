/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataitems.RecordDefinitionDataItem
DO NOT MANUALLY CHANGE THIS FILE
*/


export class RecordDefinitionDataItem {
	private _uuid: string;
	private _name: string;
	
	constructor() {
	}
	
	wsFill(webSocketReference: any) : RecordDefinitionDataItem {
		this._uuid = webSocketReference.uuid != undefined ? webSocketReference.uuid : this._uuid;
		this._name = webSocketReference.name != undefined ? webSocketReference.name : this._name;
		return this;
	}

	get uuid(): string {
		return this._uuid;
	}
	
	get name(): string {
		return this._name;
	}
	
	set uuid(value: string) {
		this._uuid = value;
	}
	
	set name(value: string) {
		this._name = value;
	}
	
}