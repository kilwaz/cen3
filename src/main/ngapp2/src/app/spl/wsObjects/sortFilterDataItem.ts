/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataitems.SortFilterDataItem
DO NOT MANUALLY CHANGE THIS FILE
*/

import {SortDataItem} from "./sortDataItem";

export class SortFilterDataItem {
	private _sorts: Array<SortDataItem> = [];
	
	constructor() {
	}
	
	wsFill(webSocketReference: any) : SortFilterDataItem {
		this._sorts = webSocketReference.sorts != undefined ? webSocketReference.sorts : this._sorts;
		return this;
	}

	get sorts(): Array<SortDataItem> {
		return this._sorts;
	}
	
	set sorts(value: Array<SortDataItem>) {
		this._sorts = value;
	}
	
}