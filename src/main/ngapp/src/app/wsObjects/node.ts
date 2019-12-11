/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
game.actors.Node
DO NOT MANUALLY CHANGE THIS FILE
*/


export class Node {
	private _value: string;
	private _right: Node;
	private _left: Node;
	private _precedence: number;
	
	constructor() {
	}
	
	wsFill(webSocketReference: any) : Node {
		this._value = webSocketReference.value != undefined ? webSocketReference.value : this._value;
		this._right = webSocketReference.right != undefined ? webSocketReference.right : this._right;
		this._left = webSocketReference.left != undefined ? webSocketReference.left : this._left;
		this._precedence = webSocketReference.precedence != undefined ? webSocketReference.precedence : this._precedence;
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
	
	get precedence(): number {
		return this._precedence;
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
	
	set precedence(value: number) {
		this._precedence = value;
	}
	
}