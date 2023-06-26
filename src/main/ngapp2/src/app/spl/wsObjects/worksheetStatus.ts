/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataitems.WorksheetStatus
DO NOT MANUALLY CHANGE THIS FILE
*/


export class WorksheetStatus {
	private _worksheetName: string;
	private _headCount: number;
	private _totalPages: number;
	private _currentPageNumber: number;
	private _pageSize: number;
	
	constructor() {
	}
	
	wsFill(webSocketReference: any) : WorksheetStatus {
		this._worksheetName = webSocketReference.worksheetName != undefined ? webSocketReference.worksheetName : this._worksheetName;
		this._headCount = webSocketReference.headCount != undefined ? webSocketReference.headCount : this._headCount;
		this._totalPages = webSocketReference.totalPages != undefined ? webSocketReference.totalPages : this._totalPages;
		this._currentPageNumber = webSocketReference.currentPageNumber != undefined ? webSocketReference.currentPageNumber : this._currentPageNumber;
		this._pageSize = webSocketReference.pageSize != undefined ? webSocketReference.pageSize : this._pageSize;
		return this;
	}

	get worksheetName(): string {
		return this._worksheetName;
	}
	
	get headCount(): number {
		return this._headCount;
	}
	
	get totalPages(): number {
		return this._totalPages;
	}
	
	get currentPageNumber(): number {
		return this._currentPageNumber;
	}
	
	get pageSize(): number {
		return this._pageSize;
	}
	
	set worksheetName(value: string) {
		this._worksheetName = value;
	}
	
	set headCount(value: number) {
		this._headCount = value;
	}
	
	set totalPages(value: number) {
		this._totalPages = value;
	}
	
	set currentPageNumber(value: number) {
		this._currentPageNumber = value;
	}
	
	set pageSize(value: number) {
		this._pageSize = value;
	}
	
}