//THIS FILE IS AUTO GENERATED - DO NOT MANUALLY CHANGE
import {Message} from "./message";

export class JoinGame extends Message {
	private _localStorageUUID: string;
	private _uuid: string;
	private _id: number;
	private _name: string;
	
	constructor() {
		super();
		this.type = "JoinGame";
	}

	decodeResponse(msgRaw: any) {
		this._uuid = msgRaw.uuid;
		this._id = msgRaw.id;
		this._name = msgRaw.name;
	}

	get localStorageUUID(): string {
		return this._localStorageUUID;
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
	
	set localStorageUUID(value: string) {
		this._localStorageUUID = value;
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
	
}