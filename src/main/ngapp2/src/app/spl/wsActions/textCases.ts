/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataobjects.TextCasesData
DO NOT MANUALLY CHANGE THIS FILE
*/

import {Message} from "./message";

export class TextCases extends Message {
	private _textToProcess: string;
	private _textFunction: number;
	private _textResult: string;
	
	constructor() {
		super();
		this.type = "TextCases";
	}

	decodeResponse(msgRaw: any) {
		this._textResult = msgRaw.textResult;
	}

	get textToProcess(): string {
		return this._textToProcess;
	}
	
	get textFunction(): number {
		return this._textFunction;
	}
	
	get textResult(): string {
		return this._textResult;
	}
	
	set textToProcess(value: string) {
		this._textToProcess = value;
	}
	
	set textFunction(value: number) {
		this._textFunction = value;
	}
	
	set textResult(value: string) {
		this._textResult = value;
	}
	
}