//THIS FILE IS AUTO GENERATED - DO NOT MANUALLY CHANGE
import {Answer} from "./answer";

export class Player {
	private _uuid: string;
	private _id: number;
	private _name: string;
	private _score: number;
	private _playerStatus: string;
	private _latestAnswer: Answer;
	
	constructor() {
	}
	
	wsFill(webSocketReference: any) : Player {
		this._uuid = webSocketReference.uuid != undefined ? webSocketReference.uuid : this._uuid;
		this._id = webSocketReference.id != undefined ? webSocketReference.id : this._id;
		this._name = webSocketReference.name != undefined ? webSocketReference.name : this._name;
		this._score = webSocketReference.score != undefined ? webSocketReference.score : this._score;
		this._playerStatus = webSocketReference.playerStatus != undefined ? webSocketReference.playerStatus : this._playerStatus;
		this._latestAnswer = webSocketReference.latestAnswer != undefined ? webSocketReference.latestAnswer : this._latestAnswer;
		return this;
	}

	get uuid(): string {
		return this._uuid;
	}
	
	get id(): number {
		return this._id;
	}
	
	get name(): string {
		return this._name;
	}
	
	get score(): number {
		return this._score;
	}
	
	get playerStatus(): string {
		return this._playerStatus;
	}
	
	get latestAnswer(): Answer {
		return this._latestAnswer;
	}
	
	set uuid(value: string) {
		this._uuid = value;
	}
	
	set id(value: number) {
		this._id = value;
	}
	
	set name(value: string) {
		this._name = value;
	}
	
	set score(value: number) {
		this._score = value;
	}
	
	set playerStatus(value: string) {
		this._playerStatus = value;
	}
	
	set latestAnswer(value: Answer) {
		this._latestAnswer = value;
	}
	
}