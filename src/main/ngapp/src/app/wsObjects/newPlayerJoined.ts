import {Message} from "./message";

export class NewPlayerJoined extends Message {
	private _newPlayerUUID: string;
	private _newPlayerID: number;
	private _newPlayerName: string;
	
	constructor() {
		super();
		this.type = "NewPlayerJoined";
	}

	decodeResponse(msgRaw: any) {
		this._newPlayerUUID = msgRaw.newPlayerUUID;
		this._newPlayerID = msgRaw.newPlayerID;
		this._newPlayerName = msgRaw.newPlayerName;
	}

	get newPlayerUUID(): string {
		return this._newPlayerUUID;
	}
	
	get newPlayerID(): number {
		return this._newPlayerID;
	}
	
	get newPlayerName(): string {
		return this._newPlayerName;
	}
	
	set newPlayerUUID(value: string) {
		this._newPlayerUUID = value;
	}
	
	set newPlayerID(value: number) {
		this._newPlayerID = value;
	}
	
	set newPlayerName(value: string) {
		this._newPlayerName = value;
	}
	
}