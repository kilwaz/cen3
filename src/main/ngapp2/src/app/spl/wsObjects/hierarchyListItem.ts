/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataitems.HierarchyListItem
DO NOT MANUALLY CHANGE THIS FILE
*/


export class HierarchyListItem {
	private _title: string;
	private _children: Array<HierarchyListItem> = [];
	
	constructor() {
	}
	
	wsFill(webSocketReference: any) : HierarchyListItem {
		this._title = webSocketReference.title != undefined ? webSocketReference.title : this._title;
		this._children = webSocketReference.children != undefined ? webSocketReference.children : this._children;
		return this;
	}

	get title(): string {
		return this._title;
	}
	
	get children(): Array<HierarchyListItem> {
		return this._children;
	}
	
	set title(value: string) {
		this._title = value;
	}
	
	set children(value: Array<HierarchyListItem>) {
		this._children = value;
	}
	
}