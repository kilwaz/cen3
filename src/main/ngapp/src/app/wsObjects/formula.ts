/*
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataitems.Formula
DO NOT MANUALLY CHANGE THIS FILE
*/

import {Node} from "./node";

export class Formula {
	private _rootNode: Node;
	private _strExpression: string;

	constructor() {
	}

	wsFill(webSocketReference: any) : Formula {
		this._rootNode = webSocketReference.rootNode != undefined ? webSocketReference.rootNode : this._rootNode;
		this._strExpression = webSocketReference.strExpression != undefined ? webSocketReference.strExpression : this._strExpression;
		return this;
	}

	get rootNode(): Node {
		return this._rootNode;
	}

	get strExpression(): string {
		return this._strExpression;
	}

	set rootNode(value: Node) {
		this._rootNode = value;
	}

	set strExpression(value: string) {
		this._strExpression = value;
	}

}
