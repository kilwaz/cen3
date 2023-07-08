/*
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataobjects.SummaryData
DO NOT MANUALLY CHANGE THIS FILE
*/

import {Message} from "./message";
import {ConfigurableUiDataItem} from "../wsObjects/configurableUiDataItem";

export class Summary extends Message {
	private _nodeReference: string;
	private _content: string;
	private _configurableUiDataItems: Array<ConfigurableUiDataItem> = [];

	constructor() {
		super();
		this.type = "Summary";
	}

	decodeResponse(msgRaw: any) {
		this._content = msgRaw.content;
		this._configurableUiDataItems = msgRaw.configurableUiDataItems;
	}

	get nodeReference(): string {
		return this._nodeReference;
	}

	get content(): string {
		return this._content;
	}

	get configurableUiDataItems(): Array<ConfigurableUiDataItem> {
		return this._configurableUiDataItems;
	}

	set nodeReference(value: string) {
		this._nodeReference = value;
	}

	set content(value: string) {
		this._content = value;
	}

	set configurableUiDataItems(value: Array<ConfigurableUiDataItem>) {
		this._configurableUiDataItems = value;
	}

}
