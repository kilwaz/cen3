/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataitems.ConfigurableUiDataItem
DO NOT MANUALLY CHANGE THIS FILE
*/

import {WebRecordDataItem} from "./webRecordDataItem";

export class ConfigurableUiDataItem {
	private _type: string;
	private _records: Array<WebRecordDataItem> = [];
	
	constructor() {
	}
	
	wsFill(webSocketReference: any) : ConfigurableUiDataItem {
		this._type = webSocketReference.type != undefined ? webSocketReference.type : this._type;
		this._records = webSocketReference.records != undefined ? webSocketReference.records : this._records;
		return this;
	}

	get type(): string {
		return this._type;
	}
	
	get records(): Array<WebRecordDataItem> {
		return this._records;
	}
	
	set type(value: string) {
		this._type = value;
	}
	
	set records(value: Array<WebRecordDataItem>) {
		this._records = value;
	}
	
}