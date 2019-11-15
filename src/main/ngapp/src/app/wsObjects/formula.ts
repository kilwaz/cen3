//THIS FILE IS AUTO GENERATED - DO NOT MANUALLY CHANGE
import {Node} from "./node";

export class Formula {
	private _rootNode: Node;
	
	constructor() {
	}
	
	wsFill(webSocketReference: any) : Formula {
		this._rootNode = webSocketReference.rootNode != undefined ? webSocketReference.rootNode : this._rootNode;
		return this;
	}

	get rootNode(): Node {
		return this._rootNode;
	}
	
	set rootNode(value: Node) {
		this._rootNode = value;
	}
	
}