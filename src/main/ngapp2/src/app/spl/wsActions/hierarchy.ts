/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataobjects.HierarchyData
DO NOT MANUALLY CHANGE THIS FILE
*/

import {Message} from "./message";
import {HierarchyListItem} from "../wsObjects/hierarchyListItem";

export class Hierarchy extends Message {
	private _hierarchyItems: Array<HierarchyListItem> = [];
	
	constructor() {
		super();
		this.type = "Hierarchy";
	}

	decodeResponse(msgRaw: any) {
		this._hierarchyItems = msgRaw.hierarchyItems;
	}

	get hierarchyItems(): Array<HierarchyListItem> {
		return this._hierarchyItems;
	}
	
	set hierarchyItems(value: Array<HierarchyListItem>) {
		this._hierarchyItems = value;
	}
	
}