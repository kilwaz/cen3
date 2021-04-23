/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataobjects.ToolTestData
DO NOT MANUALLY CHANGE THIS FILE
*/

import {Message} from "./message";

export class ToolTest extends Message {
	private _message: string;
	
	constructor() {
		super();
		this.type = "ToolTest";
	}

	decodeResponse(msgRaw: any) {
		this._message = msgRaw.message;
	}

	get message(): string {
		return this._message;
	}
	
	set message(value: string) {
		this._message = value;
	}
	
}