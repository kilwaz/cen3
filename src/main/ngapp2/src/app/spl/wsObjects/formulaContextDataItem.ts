/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataitems.FormulaContextDataItem
DO NOT MANUALLY CHANGE THIS FILE
*/


export class FormulaContextDataItem {
	private _uuid: string;
	private _name: string;
	private _definitionIds: Array<string> = [];
	
	constructor() {
	}
	
	wsFill(webSocketReference: any) : FormulaContextDataItem {
		this._uuid = webSocketReference.uuid != undefined ? webSocketReference.uuid : this._uuid;
		this._name = webSocketReference.name != undefined ? webSocketReference.name : this._name;
		this._definitionIds = webSocketReference.definitionIds != undefined ? webSocketReference.definitionIds : this._definitionIds;
		return this;
	}

	get uuid(): string {
		return this._uuid;
	}
	
	get name(): string {
		return this._name;
	}
	
	get definitionIds(): Array<string> {
		return this._definitionIds;
	}
	
	set uuid(value: string) {
		this._uuid = value;
	}
	
	set name(value: string) {
		this._name = value;
	}
	
	set definitionIds(value: Array<string>) {
		this._definitionIds = value;
	}
	
}