/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataitems.WebWorksheetConfig
DO NOT MANUALLY CHANGE THIS FILE
*/


export class WebWorksheetConfig {
	private _name: string;
	private _definitionName: string;
	
	constructor() {
	}
	
	wsFill(webSocketReference: any) : WebWorksheetConfig {
		this._name = webSocketReference.name != undefined ? webSocketReference.name : this._name;
		this._definitionName = webSocketReference.definitionName != undefined ? webSocketReference.definitionName : this._definitionName;
		return this;
	}

	get name(): string {
		return this._name;
	}
	
	get definitionName(): string {
		return this._definitionName;
	}
	
	set name(value: string) {
		this._name = value;
	}
	
	set definitionName(value: string) {
		this._definitionName = value;
	}
	
}