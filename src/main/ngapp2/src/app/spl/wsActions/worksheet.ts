/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataobjects.WorksheetData
DO NOT MANUALLY CHANGE THIS FILE
*/

import {Message} from "./message";
import {WebRecord} from "../wsObjects/webRecord";
import {WebWorksheetConfig} from "../wsObjects/webWorksheetConfig";
import {SortFilter} from "../wsObjects/sortFilter";
import {WorksheetStatus} from "../wsObjects/worksheetStatus";

export class Worksheet extends Message {
	private _requestID: string;
	private _worksheetRecords: Array<WebRecord> = [];
	private _worksheetConfig: Array<WebWorksheetConfig> = [];
	private _sortFilter: SortFilter;
	private _worksheetStatus: WorksheetStatus;
	
	constructor() {
		super();
		this.type = "Worksheet";
	}

	decodeResponse(msgRaw: any) {
		this._requestID = msgRaw.requestID;
		this._worksheetRecords = msgRaw.worksheetRecords;
		this._worksheetConfig = msgRaw.worksheetConfig;
		this._sortFilter = msgRaw.sortFilter;
		this._worksheetStatus = msgRaw.worksheetStatus;
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
	
	get worksheetStatus(): WorksheetStatus {
		return this._worksheetStatus;
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
	
	set worksheetStatus(value: WorksheetStatus) {
		this._worksheetStatus = value;
	}
	
}