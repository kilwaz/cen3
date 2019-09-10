//THIS FILE IS AUTO GENERATED - DO NOT MANUALLY CHANGE

export class QuestionOption {
	private _uuid: string;
	private _answerValue: string;
	private _countedAnswers: number;
	
	constructor() {
	}
	
	wsFill(webSocketReference: any) : QuestionOption {
		this._uuid = webSocketReference.uuid != undefined ? webSocketReference.uuid : this._uuid;
		this._answerValue = webSocketReference.answerValue != undefined ? webSocketReference.answerValue : this._answerValue;
		this._countedAnswers = webSocketReference.countedAnswers != undefined ? webSocketReference.countedAnswers : this._countedAnswers;
		return this;
	}

	get uuid(): string {
		return this._uuid;
	}
	
	get answerValue(): string {
		return this._answerValue;
	}
	
	get countedAnswers(): number {
		return this._countedAnswers;
	}
	
	set uuid(value: string) {
		this._uuid = value;
	}
	
	set answerValue(value: string) {
		this._answerValue = value;
	}
	
	set countedAnswers(value: number) {
		this._countedAnswers = value;
	}
	
}