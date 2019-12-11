/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
game.actors.Player
DO NOT MANUALLY CHANGE THIS FILE
*/


export class Player {
	private _uuid: string;
	private _id: number;
	private _name: string;
	private _score: number;
	private _playerStatus: string;
	
	constructor() {
	}
	
	wsFill(webSocketReference: any) : Player {
		this._uuid = webSocketReference.uuid != undefined ? webSocketReference.uuid : this._uuid;
		this._id = webSocketReference.id != undefined ? webSocketReference.id : this._id;
		this._name = webSocketReference.name != undefined ? webSocketReference.name : this._name;
		this._score = webSocketReference.score != undefined ? webSocketReference.score : this._score;
		this._playerStatus = webSocketReference.playerStatus != undefined ? webSocketReference.playerStatus : this._playerStatus;
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
	
}