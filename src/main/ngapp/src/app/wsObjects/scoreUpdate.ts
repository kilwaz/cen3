//THIS FILE IS AUTO GENERATED - DO NOT MANUALLY CHANGE

export class ScoreUpdate {
	private _score: number;
	private _playerUUID: string;
	
	constructor() {
	}
	
	wsFill(webSocketReference: any) : ScoreUpdate {
		this._score = webSocketReference.score != undefined ? webSocketReference.score : this._score;
		this._playerUUID = webSocketReference.playerUUID != undefined ? webSocketReference.playerUUID : this._playerUUID;
		return this;
	}

	get score(): number {
		return this._score;
	}
	
	get playerUUID(): string {
		return this._playerUUID;
	}
	
	set score(value: number) {
		this._score = value;
	}
	
	set playerUUID(value: string) {
		this._playerUUID = value;
	}
	
}