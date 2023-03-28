/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataobjects.RoleData
DO NOT MANUALLY CHANGE THIS FILE
*/

import {Message} from "./message";

export class Role extends Message {
	private _username: string;
	
	constructor() {
		super();
		this.type = "Role";
	}

	decodeResponse(msgRaw: any) {
	}

	get username(): string {
		return this._username;
	}
	
	set username(value: string) {
		this._username = value;
	}
	
}