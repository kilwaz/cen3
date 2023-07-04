/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataitems.FormulaDataItem
DO NOT MANUALLY CHANGE THIS FILE
*/

import {NodeDataItem} from "./nodeDataItem";

export class FormulaDataItem {
	private _rootNode: NodeDataItem;
	private _strExpression: string;
	
	constructor() {
	}
	
	wsFill(webSocketReference: any) : FormulaDataItem {
		this._rootNode = webSocketReference.rootNode != undefined ? webSocketReference.rootNode : this._rootNode;
		this._strExpression = webSocketReference.strExpression != undefined ? webSocketReference.strExpression : this._strExpression;
		return this;
	}

	get rootNode(): NodeDataItem {
		return this._rootNode;
	}
	
	get strExpression(): string {
		return this._strExpression;
	}
	
	set rootNode(value: NodeDataItem) {
		this._rootNode = value;
	}
	
	set strExpression(value: string) {
		this._strExpression = value;
	}
	
}