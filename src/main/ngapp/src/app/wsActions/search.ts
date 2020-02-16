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
	private _searchResults: Array<Record> = [];
	
	constructor() {
		super();
		this.type = "Search";
	}

	decodeResponse(msgRaw: any) {
		this._searchResults = msgRaw.searchResults;
	}

	get searchItem(): string {
		return this._searchItem;
	}
	
	get searchValue(): string {
		return this._searchValue;
	}
	
	get searchResults(): Array<Record> {
		return this._searchResults;
	}
	
	set searchItem(value: string) {
		this._searchItem = value;
	}
	
	set searchValue(value: string) {
		this._searchValue = value;
	}
	
	set searchResults(value: Array<Record>) {
		this._searchResults = value;
	}
	
}