//THIS FILE IS AUTO GENERATED - DO NOT MANUALLY CHANGE
import {Message} from "./message";
import {Player} from "../player";

export class AdminGame extends Message {
	private _localStorageUUID: string;
	private _adminUUID: string;
	private _allPlayers: Array<Player> = [];
	
	constructor() {
		super();
		this.type = "AdminGame";
	}

	decodeResponse(msgRaw: any) {
		this._adminUUID = msgRaw.adminUUID;
		this._allPlayers = msgRaw.allPlayers;
	}

	get localStorageUUID(): string {
		return this._localStorageUUID;
	}
	
	get adminUUID(): string {
		return this._adminUUID;
	}
	
	get allPlayers(): Array<Player> {
		return this._allPlayers;
	}
	
	set localStorageUUID(value: string) {
		this._localStorageUUID = value;
	}
	
	set adminUUID(value: string) {
		this._adminUUID = value;
	}
	
	set allPlayers(value: Array<Player>) {
		this._allPlayers = value;
	}
	
}