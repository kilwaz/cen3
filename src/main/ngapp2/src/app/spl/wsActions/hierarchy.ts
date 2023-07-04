/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataobjects.HierarchyData
DO NOT MANUALLY CHANGE THIS FILE
*/

import {Message} from "./message";
import {HierarchyListDataItem} from "../wsObjects/hierarchyListDataItem";

export class Hierarchy extends Message {
	private _hierarchyItems: Array<HierarchyListDataItem> = [];
	
	constructor() {
		super();
		this.type = "Hierarchy";
	}

	decodeResponse(msgRaw: any) {
		this._hierarchyItems = msgRaw.hierarchyItems;
	}

	get hierarchyItems(): Array<HierarchyListDataItem> {
		return this._hierarchyItems;
	}
	
	set hierarchyItems(value: Array<HierarchyListDataItem>) {
		this._hierarchyItems = value;
	}
	
}