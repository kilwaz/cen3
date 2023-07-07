/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataobjects.FilteredListData
DO NOT MANUALLY CHANGE THIS FILE
*/

import {Message} from "./message";
import {SortFilterDataItem} from "../wsObjects/sortFilterDataItem";

export class FilteredList extends Message {
	private _definition: string;
	private _nodeReference: string;
	private _sortFilter: SortFilterDataItem;
	private _listItem: Array<string> = [];
	
	constructor() {
		super();
		this.type = "FilteredList";
	}

	decodeResponse(msgRaw: any) {
		this._listItem = msgRaw.listItem;
	}

	get definition(): string {
		return this._definition;
	}
	
	get nodeReference(): string {
		return this._nodeReference;
	}
	
	get sortFilter(): SortFilterDataItem {
		return this._sortFilter;
	}
	
	get listItem(): Array<string> {
		return this._listItem;
	}
	
	set definition(value: string) {
		this._definition = value;
	}
	
	set nodeReference(value: string) {
		this._nodeReference = value;
	}
	
	set sortFilter(value: SortFilterDataItem) {
		this._sortFilter = value;
	}
	
	set listItem(value: Array<string>) {
		this._listItem = value;
	}
	
}