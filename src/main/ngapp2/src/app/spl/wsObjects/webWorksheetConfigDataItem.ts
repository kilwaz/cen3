/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataitems.WebWorksheetConfigDataItem
DO NOT MANUALLY CHANGE THIS FILE
*/


export class WebWorksheetConfigDataItem {
	private _uuid: string;
	private _name: string;
	private _definitionName: string;
	private _definitionId: string;
	private _columnOrder: number;
	private _columnType: string;
	
	constructor() {
	}
	
	wsFill(webSocketReference: any) : WebWorksheetConfigDataItem {
		this._uuid = webSocketReference.uuid != undefined ? webSocketReference.uuid : this._uuid;
		this._name = webSocketReference.name != undefined ? webSocketReference.name : this._name;
		this._definitionName = webSocketReference.definitionName != undefined ? webSocketReference.definitionName : this._definitionName;
		this._definitionId = webSocketReference.definitionId != undefined ? webSocketReference.definitionId : this._definitionId;
		this._columnOrder = webSocketReference.columnOrder != undefined ? webSocketReference.columnOrder : this._columnOrder;
		this._columnType = webSocketReference.columnType != undefined ? webSocketReference.columnType : this._columnType;
		return this;
	}

	get uuid(): string {
		return this._uuid;
	}
	
	get name(): string {
		return this._name;
	}
	
	get definitionName(): string {
		return this._definitionName;
	}
	
	get definitionId(): string {
		return this._definitionId;
	}
	
	get columnOrder(): number {
		return this._columnOrder;
	}
	
	get columnType(): string {
		return this._columnType;
	}
	
	set uuid(value: string) {
		this._uuid = value;
	}
	
	set name(value: string) {
		this._name = value;
	}
	
	set definitionName(value: string) {
		this._definitionName = value;
	}
	
	set definitionId(value: string) {
		this._definitionId = value;
	}
	
	set columnOrder(value: number) {
		this._columnOrder = value;
	}
	
	set columnType(value: string) {
		this._columnType = value;
	}
	
}