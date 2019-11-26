//THIS FILE IS AUTO GENERATED - DO NOT MANUALLY CHANGE
import {Message} from "./message";
import {Node} from "../wsObjects/node";

export class CalculateSum extends Message {
	private _formula: string;
	private _result: number;
	private _node: Node;
	
	constructor() {
		super();
		this.type = "CalculateSum";
	}

	decodeResponse(msgRaw: any) {
		this._result = msgRaw.result;
		this._node = msgRaw.node;
	}

	get formula(): string {
		return this._formula;
	}
	
	get result(): number {
		return this._result;
	}
	
	get node(): Node {
		return this._node;
	}
	
	set formula(value: string) {
		this._formula = value;
	}
	
	set result(value: number) {
		this._result = value;
	}
	
	set node(value: Node) {
		this._node = value;
	}
	
}