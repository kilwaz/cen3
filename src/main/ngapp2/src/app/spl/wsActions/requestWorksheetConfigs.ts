/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataobjects.RequestWorksheetConfigsData
DO NOT MANUALLY CHANGE THIS FILE
*/

import {Message} from "./message";
import {WorksheetConfigDataItem} from "../wsObjects/worksheetConfigDataItem";

export class RequestWorksheetConfigs extends Message {
	private _worksheetConfigs: Array<WorksheetConfigDataItem> = [];
	
	constructor() {
		super();
		this.type = "RequestWorksheetConfigs";
	}

	decodeResponse(msgRaw: any) {
		this._worksheetConfigs = msgRaw.worksheetConfigs;
	}

	get worksheetConfigs(): Array<WorksheetConfigDataItem> {
		return this._worksheetConfigs;
	}
	
	set worksheetConfigs(value: Array<WorksheetConfigDataItem>) {
		this._worksheetConfigs = value;
	}
	
}