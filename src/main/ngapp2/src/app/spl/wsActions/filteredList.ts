/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataobjects.FilteredListData
DO NOT MANUALLY CHANGE THIS FILE
*/

import {Message} from "./message";

export class FilteredList extends Message {
	private _definition: string;
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
	
	get listItem(): Array<string> {
		return this._listItem;
	}
	
	set definition(value: string) {
		this._definition = value;
	}
	
	set listItem(value: Array<string>) {
		this._listItem = value;
	}
	
}