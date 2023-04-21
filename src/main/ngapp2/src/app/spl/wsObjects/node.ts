/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataitems.Node
DO NOT MANUALLY CHANGE THIS FILE
*/


export class Node {
	private _value: string;
	private _right: Node;
	private _left: Node;
	private _nodeList: Array<Node> = [];
	private _nodeType: number;
	
	constructor() {
	}
	
	wsFill(webSocketReference: any) : Node {
		this._value = webSocketReference.value != undefined ? webSocketReference.value : this._value;
		this._right = webSocketReference.right != undefined ? webSocketReference.right : this._right;
		this._left = webSocketReference.left != undefined ? webSocketReference.left : this._left;
		this._nodeList = webSocketReference.nodeList != undefined ? webSocketReference.nodeList : this._nodeList;
		this._nodeType = webSocketReference.nodeType != undefined ? webSocketReference.nodeType : this._nodeType;
		return this;
	}

	get value(): string {
		return this._value;
	}
	
	get right(): Node {
		return this._right;
	}
	
	get left(): Node {
		return this._left;
	}
	
	get nodeList(): Array<Node> {
		return this._nodeList;
	}
	
	get nodeType(): number {
		return this._nodeType;
	}
	
	set value(value: string) {
		this._value = value;
	}
	
	set right(value: Node) {
		this._right = value;
	}
	
	set left(value: Node) {
		this._left = value;
	}
	
	set nodeList(value: Array<Node>) {
		this._nodeList = value;
	}
	
	set nodeType(value: number) {
		this._nodeType = value;
	}
	
}