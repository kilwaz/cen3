/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
game.actors.Record
DO NOT MANUALLY CHANGE THIS FILE
*/


export class Record {
	private _uuid: string;
	
	constructor() {
	}
	
	wsFill(webSocketReference: any) : Record {
		this._uuid = webSocketReference.uuid != undefined ? webSocketReference.uuid : this._uuid;
		return this;
	}

	get uuid(): string {
		return this._uuid;
	}
	
	set uuid(value: string) {
		this._uuid = value;
	}
	
}