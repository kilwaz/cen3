/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataobjects.EchoData
DO NOT MANUALLY CHANGE THIS FILE
*/

import {Message} from "./message";

export class Echo extends Message {
	private _echoResponse: string;
	
	constructor() {
		super();
		this.type = "Echo";
	}

	decodeResponse(msgRaw: any) {
		this._echoResponse = msgRaw.echoResponse;
	}

	get echoResponse(): string {
		return this._echoResponse;
	}
	
	set echoResponse(value: string) {
		this._echoResponse = value;
	}
	
}