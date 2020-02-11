/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
game.actors.Entry
DO NOT MANUALLY CHANGE THIS FILE
*/


export class Entry {
	private _uuid: string;
	private _value: string;
	
	constructor() {
	}
	
	wsFill(webSocketReference: any) : Entry {
		this._uuid = webSocketReference.uuid != undefined ? webSocketReference.uuid : this._uuid;
		this._value = webSocketReference.value != undefined ? webSocketReference.value : this._value;
		return this;
	}

	get uuid(): string {
		return this._uuid;
	}
	
	get value(): string {
		return this._value;
	}
	
	set uuid(value: string) {
		this._uuid = value;
	}
	
	set value(value: string) {
		this._value = value;
	}
	
}