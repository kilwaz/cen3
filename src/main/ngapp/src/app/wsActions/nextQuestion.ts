//THIS FILE IS AUTO GENERATED - DO NOT MANUALLY CHANGE
import {Message} from "./message";
import {QuestionOption} from "../wsObjects/questionOption";

export class NextQuestion extends Message {
	private _nextQuestionUUID: string;
	private _questionText: string;
	private _options: Array<QuestionOption> = [];
	
	constructor() {
		super();
		this.type = "NextQuestion";
	}

	decodeResponse(msgRaw: any) {
		this._nextQuestionUUID = msgRaw.nextQuestionUUID;
		this._questionText = msgRaw.questionText;
		this._options = msgRaw.options;
	}

	get nextQuestionUUID(): string {
		return this._nextQuestionUUID;
	}
	
	get questionText(): string {
		return this._questionText;
	}
	
	get options(): Array<QuestionOption> {
		return this._options;
	}
	
	set nextQuestionUUID(value: string) {
		this._nextQuestionUUID = value;
	}
	
	set questionText(value: string) {
		this._questionText = value;
	}
	
	set options(value: Array<QuestionOption>) {
		this._options = value;
	}
	
}