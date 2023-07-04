/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataobjects.MenuData
DO NOT MANUALLY CHANGE THIS FILE
*/

import {Message} from "./message";
import {MenuDataItem} from "../wsObjects/menuDataItem";

export class Menu extends Message {
	private _menuItems: Array<MenuDataItem> = [];
	
	constructor() {
		super();
		this.type = "Menu";
	}

	decodeResponse(msgRaw: any) {
		this._menuItems = msgRaw.menuItems;
	}

	get menuItems(): Array<MenuDataItem> {
		return this._menuItems;
	}
	
	set menuItems(value: Array<MenuDataItem>) {
		this._menuItems = value;
	}
	
}