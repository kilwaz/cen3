//THIS FILE IS AUTO GENERATED - DO NOT MANUALLY CHANGE
import {Player} from "./player";

export class Answer {
	private _uuid: string;
	private _answerValue: string;
	private _player: Player;
	
	constructor() {
	}
	
	wsFill(webSocketReference: any) : Answer {
		this._uuid = webSocketReference.uuid != undefined ? webSocketReference.uuid : this._uuid;
		this._answerValue = webSocketReference.answerValue != undefined ? webSocketReference.answerValue : this._answerValue;
		this._player = webSocketReference.player != undefined ? webSocketReference.player : this._player;
		return this;
	}

	get uuid(): string {
		return this._uuid;
	}
	
	get answerValue(): string {
		return this._answerValue;
	}
	
	get player(): Player {
		return this._player;
	}
	
	set uuid(value: string) {
		this._uuid = value;
	}
	
	set answerValue(value: string) {
		this._answerValue = value;
	}
	
	set player(value: Player) {
		this._player = value;
	}
	
}