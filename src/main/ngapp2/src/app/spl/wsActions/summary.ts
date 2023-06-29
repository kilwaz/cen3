/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataobjects.SummaryData
DO NOT MANUALLY CHANGE THIS FILE
*/

import {Message} from "./message";

export class Summary extends Message {
	private _requestID: string;
	private _content: string;
	
	constructor() {
		super();
		this.type = "Summary";
	}

	decodeResponse(msgRaw: any) {
		this._requestID = msgRaw.requestID;
		this._content = msgRaw.content;
	}

	get requestID(): string {
		return this._requestID;
	}
	
	get content(): string {
		return this._content;
	}
	
	set requestID(value: string) {
		this._requestID = value;
	}
	
	set content(value: string) {
		this._content = value;
	}
	
}