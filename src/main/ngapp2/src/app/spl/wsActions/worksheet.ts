/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataobjects.WorksheetData
DO NOT MANUALLY CHANGE THIS FILE
*/

import {Message} from "./message";
import {WebRecord} from "../wsObjects/webRecord";

export class Worksheet extends Message {
	private _requestID: string;
	private _worksheetRecords: Array<WebRecord> = [];
	
	constructor() {
		super();
		this.type = "Worksheet";
	}

	decodeResponse(msgRaw: any) {
		this._requestID = msgRaw.requestID;
		this._worksheetRecords = msgRaw.worksheetRecords;
	}

	get requestID(): string {
		return this._requestID;
	}
	
	get worksheetRecords(): Array<WebRecord> {
		return this._worksheetRecords;
	}
	
	set requestID(value: string) {
		this._requestID = value;
	}
	
	set worksheetRecords(value: Array<WebRecord>) {
		this._worksheetRecords = value;
	}
	
}