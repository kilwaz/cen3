/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataitems.HierarchyListDataItem
DO NOT MANUALLY CHANGE THIS FILE
*/


export class HierarchyListDataItem {
	private _title: string;
	private _nodeReference: string;
	private _expanded: boolean;
	private _childrenIds: Array<string> = [];
	
	constructor() {
	}
	
	wsFill(webSocketReference: any) : HierarchyListDataItem {
		this._title = webSocketReference.title != undefined ? webSocketReference.title : this._title;
		this._nodeReference = webSocketReference.nodeReference != undefined ? webSocketReference.nodeReference : this._nodeReference;
		this._expanded = webSocketReference.expanded != undefined ? webSocketReference.expanded : this._expanded;
		this._childrenIds = webSocketReference.childrenIds != undefined ? webSocketReference.childrenIds : this._childrenIds;
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
	
	get childrenIds(): Array<string> {
		return this._childrenIds;
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
	
	set childrenIds(value: Array<string>) {
		this._childrenIds = value;
	}
	
}