/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataitems.SummarySection
DO NOT MANUALLY CHANGE THIS FILE
*/


export class SummarySection {
	private _title: string;
	
	constructor() {
	}
	
	wsFill(webSocketReference: any) : SummarySection {
		this._title = webSocketReference.title != undefined ? webSocketReference.title : this._title;
		return this;
	}

	get title(): string {
		return this._title;
	}
	
	set title(value: string) {
		this._title = value;
	}
	
}