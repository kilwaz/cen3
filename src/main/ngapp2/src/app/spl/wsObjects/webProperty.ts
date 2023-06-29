/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataitems.WebProperty
DO NOT MANUALLY CHANGE THIS FILE
*/


export class WebProperty {
	private _name: string;
	private _value: string;
	
	constructor() {
	}
	
	wsFill(webSocketReference: any) : WebProperty {
		this._name = webSocketReference.name != undefined ? webSocketReference.name : this._name;
		this._value = webSocketReference.value != undefined ? webSocketReference.value : this._value;
		return this;
	}

	get name(): string {
		return this._name;
	}
	
	get value(): string {
		return this._value;
	}
	
	set name(value: string) {
		this._name = value;
	}
	
	set value(value: string) {
		this._value = value;
	}
	
}