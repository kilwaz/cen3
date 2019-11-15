//THIS FILE IS AUTO GENERATED - DO NOT MANUALLY CHANGE

export class Node {
	private _value: string;
	private _right: Node;
	private _left: Node;
	
	constructor() {
	}
	
	wsFill(webSocketReference: any) : Node {
		this._value = webSocketReference.value != undefined ? webSocketReference.value : this._value;
		this._right = webSocketReference.right != undefined ? webSocketReference.right : this._right;
		this._left = webSocketReference.left != undefined ? webSocketReference.left : this._left;
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
	
	set value(value: string) {
		this._value = value;
	}
	
	set right(value: Node) {
		this._right = value;
	}
	
	set left(value: Node) {
		this._left = value;
	}
	
}