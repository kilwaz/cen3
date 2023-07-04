/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataobjects.WorksheetData
DO NOT MANUALLY CHANGE THIS FILE
*/

import {Message} from "./message";
import {WebRecordDataItem} from "../wsObjects/webRecordDataItem";
import {WebWorksheetConfigDataItem} from "../wsObjects/webWorksheetConfigDataItem";
import {SortFilterDataItem} from "../wsObjects/sortFilterDataItem";
import {WorksheetStatusDataItem} from "../wsObjects/worksheetStatusDataItem";

export class Worksheet extends Message {
	private _requestID: string;
	private _worksheetRecords: Array<WebRecordDataItem> = [];
	private _worksheetConfig: Array<WebWorksheetConfigDataItem> = [];
	private _sortFilter: SortFilterDataItem;
	private _worksheetStatus: WorksheetStatusDataItem;
	
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
	
	get worksheetRecords(): Array<WebRecordDataItem> {
		return this._worksheetRecords;
	}
	
	get worksheetConfig(): Array<WebWorksheetConfigDataItem> {
		return this._worksheetConfig;
	}
	
	get sortFilter(): SortFilterDataItem {
		return this._sortFilter;
	}
	
	get worksheetStatus(): WorksheetStatusDataItem {
		return this._worksheetStatus;
	}
	
	set requestID(value: string) {
		this._requestID = value;
	}
	
	set worksheetRecords(value: Array<WebRecordDataItem>) {
		this._worksheetRecords = value;
	}
	
	set worksheetConfig(value: Array<WebWorksheetConfigDataItem>) {
		this._worksheetConfig = value;
	}
	
	set sortFilter(value: SortFilterDataItem) {
		this._sortFilter = value;
	}
	
	set worksheetStatus(value: WorksheetStatusDataItem) {
		this._worksheetStatus = value;
	}
	
}