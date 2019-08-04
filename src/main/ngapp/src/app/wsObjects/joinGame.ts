import {Message} from "./message";

export class JoinGame extends Message {
	private _localStorageUUID: string;
	private _playerUUID: string;
	private _playerID: number;
	private _playerName: string;
	
	constructor() {
		super();
		this.type = "JoinGame";
	}

	decodeResponse(msgRaw: any) {
		this._playerUUID = msgRaw.playerUUID;
		this._playerID = msgRaw.playerID;
		this._playerName = msgRaw.playerName;
	}

	get localStorageUUID(): string {
		return this._localStorageUUID;
	}
	
	get playerUUID(): string {
		return this._playerUUID;
	}
	
	get playerID(): number {
		return this._playerID;
	}
	
	get playerName(): string {
		return this._playerName;
	}
	
	set localStorageUUID(value: string) {
		this._localStorageUUID = value;
	}
	
	set playerUUID(value: string) {
		this._playerUUID = value;
	}
	
	set playerID(value: number) {
		this._playerID = value;
	}
	
	set playerName(value: string) {
		this._playerName = value;
	}
	
}