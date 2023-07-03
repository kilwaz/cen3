/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataobjects.ExportConfigurationData
DO NOT MANUALLY CHANGE THIS FILE
*/

import {Message} from "./message";
import {DownloadFileDataItem} from "../wsObjects/downloadFileDataItem";

export class ExportConfiguration extends Message {
	private _downloadFile: DownloadFileDataItem;
	
	constructor() {
		super();
		this.type = "ExportConfiguration";
	}

	decodeResponse(msgRaw: any) {
		this._downloadFile = msgRaw.downloadFile;
	}

	get downloadFile(): DownloadFileDataItem {
		return this._downloadFile;
	}
	
	set downloadFile(value: DownloadFileDataItem) {
		this._downloadFile = value;
	}
	
}