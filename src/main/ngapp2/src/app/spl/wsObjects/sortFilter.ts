/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataitems.SortFilter
DO NOT MANUALLY CHANGE THIS FILE
*/

import {SortItem} from "./sortItem";

export class SortFilter {
	private _sorts: Array<SortItem> = [];
	
	constructor() {
	}
	
	wsFill(webSocketReference: any) : SortFilter {
		this._sorts = webSocketReference.sorts != undefined ? webSocketReference.sorts : this._sorts;
		return this;
	}

	get sorts(): Array<SortItem> {
		return this._sorts;
	}
	
	set sorts(value: Array<SortItem>) {
		this._sorts = value;
	}
	
}