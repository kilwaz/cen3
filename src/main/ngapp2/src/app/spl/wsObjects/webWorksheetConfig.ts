/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataitems.WebWorksheetConfig
DO NOT MANUALLY CHANGE THIS FILE
*/


export class WebWorksheetConfig {
	private _name: string;
	
	constructor() {
	}
	
	wsFill(webSocketReference: any) : WebWorksheetConfig {
		this._name = webSocketReference.name != undefined ? webSocketReference.name : this._name;
		return this;
	}

	get name(): string {
		return this._name;
	}
	
	set name(value: string) {
		this._name = value;
	}
	
}