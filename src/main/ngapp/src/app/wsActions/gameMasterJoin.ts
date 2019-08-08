//THIS FILE IS AUTO GENERATED - DO NOT MANUALLY CHANGE
import {Message} from "./message";
import {Player} from "../player";

export class GameMasterJoin extends Message {
	private _localStorageUUID: string;
	private _gameMasterUUID: string;
	private _players: Array<Player> = [];
	
	constructor() {
		super();
		this.type = "GameMasterJoin";
	}

	decodeResponse(msgRaw: any) {
		this._gameMasterUUID = msgRaw.gameMasterUUID;
		this._players = msgRaw.players;
	}

	get localStorageUUID(): string {
		return this._localStorageUUID;
	}
	
	get gameMasterUUID(): string {
		return this._gameMasterUUID;
	}
	
	get players(): Array<Player> {
		return this._players;
	}
	
	set localStorageUUID(value: string) {
		this._localStorageUUID = value;
	}
	
	set gameMasterUUID(value: string) {
		this._gameMasterUUID = value;
	}
	
	set players(value: Array<Player>) {
		this._players = value;
	}
	
}