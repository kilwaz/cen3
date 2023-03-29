/*
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
game.actors.MenuItem
DO NOT MANUALLY CHANGE THIS FILE
*/

// import {MenuItem} from "./menuItem";

export class MenuItem {
	private _routeLink: string;
	private _title: string;
	private _icon: string;
	private _type: string;
	private _children: Array<MenuItem> = [];

	constructor() {
	}

	wsFill(webSocketReference: any) : MenuItem {
		this._routeLink = webSocketReference.routeLink != undefined ? webSocketReference.routeLink : this._routeLink;
		this._title = webSocketReference.title != undefined ? webSocketReference.title : this._title;
		this._icon = webSocketReference.icon != undefined ? webSocketReference.icon : this._icon;
		this._type = webSocketReference.type != undefined ? webSocketReference.type : this._type;
		this._children = webSocketReference.children != undefined ? webSocketReference.children : this._children;
		return this;
	}

	get routeLink(): string {
		return this._routeLink;
	}

	get title(): string {
		return this._title;
	}

	get icon(): string {
		return this._icon;
	}

	get type(): string {
		return this._type;
	}

	get children(): Array<MenuItem> {
		return this._children;
	}

	set routeLink(value: string) {
		this._routeLink = value;
	}

	set title(value: string) {
		this._title = value;
	}

	set icon(value: string) {
		this._icon = value;
	}

	set type(value: string) {
		this._type = value;
	}

	set children(value: Array<MenuItem>) {
		this._children = value;
	}

}
