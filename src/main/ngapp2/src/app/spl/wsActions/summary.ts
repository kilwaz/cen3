/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataobjects.SummaryData
DO NOT MANUALLY CHANGE THIS FILE
*/

import {Message} from "./message";

export class Summary extends Message {
	private _nodeReference: string;
	private _content: string;
	
	constructor() {
		super();
		this.type = "Summary";
	}

	decodeResponse(msgRaw: any) {
		this._nodeReference = msgRaw.nodeReference;
		this._content = msgRaw.content;
	}

	get nodeReference(): string {
		return this._nodeReference;
	}
	
	get content(): string {
		return this._content;
	}
	
	set nodeReference(value: string) {
		this._nodeReference = value;
	}
	
	set content(value: string) {
		this._content = value;
	}
	
}