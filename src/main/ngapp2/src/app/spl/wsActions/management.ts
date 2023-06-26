/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataobjects.ManagementData
DO NOT MANUALLY CHANGE THIS FILE
*/

import {Message} from "./message";

export class Management extends Message {
	private _totalMemory: number;
	private _freeMemory: number;
	private _maxMemory: number;
	
	constructor() {
		super();
		this.type = "Management";
	}

	decodeResponse(msgRaw: any) {
		this._totalMemory = msgRaw.totalMemory;
		this._freeMemory = msgRaw.freeMemory;
		this._maxMemory = msgRaw.maxMemory;
	}

	get totalMemory(): number {
		return this._totalMemory;
	}
	
	get freeMemory(): number {
		return this._freeMemory;
	}
	
	get maxMemory(): number {
		return this._maxMemory;
	}
	
	set totalMemory(value: number) {
		this._totalMemory = value;
	}
	
	set freeMemory(value: number) {
		this._freeMemory = value;
	}
	
	set maxMemory(value: number) {
		this._maxMemory = value;
	}
	
}