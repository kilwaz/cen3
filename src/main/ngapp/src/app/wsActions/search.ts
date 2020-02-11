/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataobjects.SearchData
DO NOT MANUALLY CHANGE THIS FILE
*/

import {Message} from "./message";
import {Record} from "../wsObjects/record";

export class Search extends Message {
	private _searchItem: string;
	private _searchValue: string;
	private _record: Record;
	
	constructor() {
		super();
		this.type = "Search";
	}

	decodeResponse(msgRaw: any) {
		this._record = msgRaw.record;
	}

	get searchItem(): string {
		return this._searchItem;
	}
	
	get searchValue(): string {
		return this._searchValue;
	}
	
	get record(): Record {
		return this._record;
	}
	
	set searchItem(value: string) {
		this._searchItem = value;
	}
	
	set searchValue(value: string) {
		this._searchValue = value;
	}
	
	set record(value: Record) {
		this._record = value;
	}
	
}