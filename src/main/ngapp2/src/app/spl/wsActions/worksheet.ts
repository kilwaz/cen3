/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataobjects.WorksheetData
DO NOT MANUALLY CHANGE THIS FILE
*/

import {Message} from "./message";
import {WebRecord} from "../wsObjects/webRecord";
import {WebWorksheetConfig} from "../wsObjects/webWorksheetConfig";
import {SortFilter} from "../wsObjects/sortFilter";

export class Worksheet extends Message {
	private _requestID: string;
	private _worksheetRecords: Array<WebRecord> = [];
	private _worksheetConfig: Array<WebWorksheetConfig> = [];
	private _sortFilter: SortFilter;
	
	constructor() {
		super();
		this.type = "Worksheet";
	}

	decodeResponse(msgRaw: any) {
		this._requestID = msgRaw.requestID;
		this._worksheetRecords = msgRaw.worksheetRecords;
		this._worksheetConfig = msgRaw.worksheetConfig;
		this._sortFilter = msgRaw.sortFilter;
	}

	get requestID(): string {
		return this._requestID;
	}
	
	get worksheetRecords(): Array<WebRecord> {
		return this._worksheetRecords;
	}
	
	get worksheetConfig(): Array<WebWorksheetConfig> {
		return this._worksheetConfig;
	}
	
	get sortFilter(): SortFilter {
		return this._sortFilter;
	}
	
	set requestID(value: string) {
		this._requestID = value;
	}
	
	set worksheetRecords(value: Array<WebRecord>) {
		this._worksheetRecords = value;
	}
	
	set worksheetConfig(value: Array<WebWorksheetConfig>) {
		this._worksheetConfig = value;
	}
	
	set sortFilter(value: SortFilter) {
		this._sortFilter = value;
	}
	
}