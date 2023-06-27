/*
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataitems.HierarchyListItem
DO NOT MANUALLY CHANGE THIS FILE
*/

export class HierarchyListItem {
	private _title: string;
	private _nodeReference: string;
	private _expanded: boolean;
	private _children: Array<HierarchyListItem> = [];
	private _childrenId: Array<string> = [];

	constructor() {
	}

	wsFill(webSocketReference: any) : HierarchyListItem {
		this._title = webSocketReference.title != undefined ? webSocketReference.title : this._title;
		this._nodeReference = webSocketReference.nodeReference != undefined ? webSocketReference.nodeReference : this._nodeReference;
		this._expanded = webSocketReference.expanded != undefined ? webSocketReference.expanded : this._expanded;
		this._children = webSocketReference.children != undefined ? webSocketReference.children : this._children;
		this._childrenId = webSocketReference.childrenId != undefined ? webSocketReference.childrenId : this._childrenId;
		return this;
	}

	get title(): string {
		return this._title;
	}

	get nodeReference(): string {
		return this._nodeReference;
	}

	get expanded(): boolean {
		return this._expanded;
	}

	get children(): Array<HierarchyListItem> {
		return this._children;
	}

	get childrenId(): Array<string> {
		return this._childrenId;
	}

	set title(value: string) {
		this._title = value;
	}

	set nodeReference(value: string) {
		this._nodeReference = value;
	}

	set expanded(value: boolean) {
		this._expanded = value;
	}

	set children(value: Array<HierarchyListItem>) {
		this._children = value;
	}

	set childrenId(value: Array<string>) {
		this._childrenId = value;
	}

}
