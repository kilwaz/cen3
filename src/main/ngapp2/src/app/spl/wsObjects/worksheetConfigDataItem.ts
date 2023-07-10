/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataitems.WorksheetConfigDataItem
DO NOT MANUALLY CHANGE THIS FILE
*/

import {WebWorksheetConfigDataItem} from "./webWorksheetConfigDataItem";

export class WorksheetConfigDataItem {
	private _name: string;
	private _worksheetConfigDetails: Array<WebWorksheetConfigDataItem> = [];
	
	constructor() {
	}
	
	wsFill(webSocketReference: any) : WorksheetConfigDataItem {
		this._name = webSocketReference.name != undefined ? webSocketReference.name : this._name;
		this._worksheetConfigDetails = webSocketReference.worksheetConfigDetails != undefined ? webSocketReference.worksheetConfigDetails : this._worksheetConfigDetails;
		return this;
	}

	get name(): string {
		return this._name;
	}
	
	get worksheetConfigDetails(): Array<WebWorksheetConfigDataItem> {
		return this._worksheetConfigDetails;
	}
	
	set name(value: string) {
		this._name = value;
	}
	
	set worksheetConfigDetails(value: Array<WebWorksheetConfigDataItem>) {
		this._worksheetConfigDetails = value;
	}
	
}