//THIS FILE IS AUTO GENERATED - DO NOT MANUALLY CHANGE
import {Message} from "./message";

export class DefinitionUpdate extends Message {
	private _definitionName: string;
	
	constructor() {
		super();
		this.type = "DefinitionUpdate";
	}

	decodeResponse(msgRaw: any) {
	}

	get definitionName(): string {
		return this._definitionName;
	}
	
	set definitionName(value: string) {
		this._definitionName = value;
	}
	
}