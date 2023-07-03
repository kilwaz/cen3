/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataitems.DownloadFileDataItem
DO NOT MANUALLY CHANGE THIS FILE
*/


export class DownloadFileDataItem {
	private _content: string;
	private _fileName: string;
	
	constructor() {
	}
	
	wsFill(webSocketReference: any) : DownloadFileDataItem {
		this._content = webSocketReference.content != undefined ? webSocketReference.content : this._content;
		this._fileName = webSocketReference.fileName != undefined ? webSocketReference.fileName : this._fileName;
		return this;
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