//THIS FILE IS AUTO GENERATED - DO NOT MANUALLY CHANGE

export class PlayerResult {
	private _playerUUID: string;
	private _isCorrectAnswer: boolean;
	
	constructor() {
	}
	
	wsFill(webSocketReference: any) : PlayerResult {
		this._playerUUID = webSocketReference.playerUUID;
		this._isCorrectAnswer = webSocketReference.isCorrectAnswer;
		return this;
	}

	get playerUUID(): string {
		return this._playerUUID;
	}
	
	get isCorrectAnswer(): boolean {
		return this._isCorrectAnswer;
	}
	
	set playerUUID(value: string) {
		this._playerUUID = value;
	}
	
	set isCorrectAnswer(value: boolean) {
		this._isCorrectAnswer = value;
	}
	
}