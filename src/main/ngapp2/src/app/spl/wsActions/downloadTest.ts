/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataobjects.DownloadTestData
DO NOT MANUALLY CHANGE THIS FILE
*/

import {Message} from "./message";

export class DownloadTest extends Message {
	private _content: string;
	private _fileName: string;
	
	constructor() {
		super();
		this.type = "DownloadTest";
	}

	decodeResponse(msgRaw: any) {
		this._content = msgRaw.content;
		this._fileName = msgRaw.fileName;
	}

	get content(): string {
		return this._content;
	}
	
	get fileName(): string {
		return this._fileName;
	}
	
	set content(value: string) {
		this._content = value;
	}
	
	set fileName(value: string) {
		this._fileName = value;
	}
	
}