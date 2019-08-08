//THIS FILE IS AUTO GENERATED - DO NOT MANUALLY CHANGE
import {Message} from "./message";

export class PlayerNameChange extends Message {
	private _playerUUID: string;
	private _playerName: string;
	
	constructor() {
		super();
		this.type = "PlayerNameChange";
	}

	decodeResponse(msgRaw: any) {
	}

	get playerUUID(): string {
		return this._playerUUID;
	}
	
	get playerName(): string {
		return this._playerName;
	}
	
	set playerUUID(value: string) {
		this._playerUUID = value;
	}
	
	set playerName(value: string) {
		this._playerName = value;
	}
	
}