/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataitems.WebWorksheetConfig
DO NOT MANUALLY CHANGE THIS FILE
*/


export class WebWorksheetConfig {
	private _name: string;
	private _definitionName: string;
	private _columnType: string;
	
	constructor() {
	}
	
	wsFill(webSocketReference: any) : WebWorksheetConfig {
		this._name = webSocketReference.name != undefined ? webSocketReference.name : this._name;
		this._definitionName = webSocketReference.definitionName != undefined ? webSocketReference.definitionName : this._definitionName;
		this._columnType = webSocketReference.columnType != undefined ? webSocketReference.columnType : this._columnType;
		return this;
	}

	get name(): string {
		return this._name;
	}
	
	get definitionName(): string {
		return this._definitionName;
	}
	
	get columnType(): string {
		return this._columnType;
	}
	
	set name(value: string) {
		this._name = value;
	}
	
	set definitionName(value: string) {
		this._definitionName = value;
	}
	
	set columnType(value: string) {
		this._columnType = value;
	}
	
}