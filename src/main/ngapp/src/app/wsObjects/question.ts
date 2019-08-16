//THIS FILE IS AUTO GENERATED - DO NOT MANUALLY CHANGE
import {QuestionOption} from "./questionOption";

export class Question {
	private _uuid: string;
	private _possibleOptions: Array<QuestionOption> = [];
	private _correctOption: QuestionOption;
	private _questionText: string;
	private _totalAnswers: number;
	
	constructor() {
	}
	
	wsFill(webSocketReference: any) : Question {
		this._uuid = webSocketReference.uuid;
		this._possibleOptions = webSocketReference.possibleOptions;
		this._correctOption = webSocketReference.correctOption;
		this._questionText = webSocketReference.questionText;
		this._totalAnswers = webSocketReference.totalAnswers;
		return this;
	}

	get uuid(): string {
		return this._uuid;
	}
	
	get possibleOptions(): Array<QuestionOption> {
		return this._possibleOptions;
	}
	
	get correctOption(): QuestionOption {
		return this._correctOption;
	}
	
	get questionText(): string {
		return this._questionText;
	}
	
	get totalAnswers(): number {
		return this._totalAnswers;
	}
	
	set uuid(value: string) {
		this._uuid = value;
	}
	
	set possibleOptions(value: Array<QuestionOption>) {
		this._possibleOptions = value;
	}
	
	set correctOption(value: QuestionOption) {
		this._correctOption = value;
	}
	
	set questionText(value: string) {
		this._questionText = value;
	}
	
	set totalAnswers(value: number) {
		this._totalAnswers = value;
	}
	
}