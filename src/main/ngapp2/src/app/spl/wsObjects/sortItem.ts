/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataitems.SortItem
DO NOT MANUALLY CHANGE THIS FILE
*/


export class SortItem {
	private _definitionName: string;
	private _direction: string;
	
	constructor() {
	}
	
	wsFill(webSocketReference: any) : SortItem {
		this._definitionName = webSocketReference.definitionName != undefined ? webSocketReference.definitionName : this._definitionName;
		this._direction = webSocketReference.direction != undefined ? webSocketReference.direction : this._direction;
		return this;
	}

	get definitionName(): string {
		return this._definitionName;
	}
	
	get direction(): string {
		return this._direction;
	}
	
	set definitionName(value: string) {
		this._definitionName = value;
	}
	
	set direction(value: string) {
		this._direction = value;
	}
	
}