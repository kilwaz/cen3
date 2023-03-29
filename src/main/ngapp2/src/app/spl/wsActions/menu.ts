/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataobjects.MenuData
DO NOT MANUALLY CHANGE THIS FILE
*/

import {Message} from "./message";
import {MenuItem} from "../wsObjects/menuItem";

export class Menu extends Message {
	private _menuItems: Array<MenuItem> = [];
	
	constructor() {
		super();
		this.type = "Menu";
	}

	decodeResponse(msgRaw: any) {
		this._menuItems = msgRaw.menuItems;
	}

	get menuItems(): Array<MenuItem> {
		return this._menuItems;
	}
	
	set menuItems(value: Array<MenuItem>) {
		this._menuItems = value;
	}
	
}