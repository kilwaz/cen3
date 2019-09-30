//THIS FILE IS AUTO GENERATED - DO NOT MANUALLY CHANGE

export class QuestionOption {
	private _uuid: string;
	private _answerValue: string;
	private _countedAnswers: number;
	private _isCorrectAnswer: boolean;
	private _optionPercentageOfTotalAnswers: number;
	private _answerProgressClass: string;
	
	constructor() {
	}
	
	wsFill(webSocketReference: any) : QuestionOption {
		this._uuid = webSocketReference.uuid != undefined ? webSocketReference.uuid : this._uuid;
		this._answerValue = webSocketReference.answerValue != undefined ? webSocketReference.answerValue : this._answerValue;
		this._countedAnswers = webSocketReference.countedAnswers != undefined ? webSocketReference.countedAnswers : this._countedAnswers;
		this._isCorrectAnswer = webSocketReference.isCorrectAnswer != undefined ? webSocketReference.isCorrectAnswer : this._isCorrectAnswer;
		this._optionPercentageOfTotalAnswers = webSocketReference.optionPercentageOfTotalAnswers != undefined ? webSocketReference.optionPercentageOfTotalAnswers : this._optionPercentageOfTotalAnswers;
		this._answerProgressClass = webSocketReference.answerProgressClass != undefined ? webSocketReference.answerProgressClass : this._answerProgressClass;
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
	
	get isCorrectAnswer(): boolean {
		return this._isCorrectAnswer;
	}
	
	get optionPercentageOfTotalAnswers(): number {
		return this._optionPercentageOfTotalAnswers;
	}
	
	get answerProgressClass(): string {
		return this._answerProgressClass;
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
	
	set isCorrectAnswer(value: boolean) {
		this._isCorrectAnswer = value;
	}
	
	set optionPercentageOfTotalAnswers(value: number) {
		this._optionPercentageOfTotalAnswers = value;
	}
	
	set answerProgressClass(value: string) {
		this._answerProgressClass = value;
	}
	
}