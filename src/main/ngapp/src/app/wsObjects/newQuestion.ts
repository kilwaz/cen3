import {Message} from "./message";

export class NewQuestion extends Message {
	private _questionText: string;
	
	constructor() {
		super();
		this.type = "NewQuestion";
	}

	decodeResponse(msgRaw: any) {
		this._questionText = msgRaw.questionText;
	}

	get questionText(): string {
		return this._questionText;
	}
	
	set questionText(value: string) {
		this._questionText = value;
	}
	
}