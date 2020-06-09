/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataobjects.LoginData
DO NOT MANUALLY CHANGE THIS FILE
*/

import {Message} from "./message";

export class Login extends Message {
	private _username: string;
	private _password: string;
	private _acceptedAuth: boolean;
	
	constructor() {
		super();
		this.type = "Login";
	}

	decodeResponse(msgRaw: any) {
		this._acceptedAuth = msgRaw.acceptedAuth;
	}

	get username(): string {
		return this._username;
	}
	
	get password(): string {
		return this._password;
	}
	
	get acceptedAuth(): boolean {
		return this._acceptedAuth;
	}
	
	set username(value: string) {
		this._username = value;
	}
	
	set password(value: string) {
		this._password = value;
	}
	
	set acceptedAuth(value: boolean) {
		this._acceptedAuth = value;
	}
	
}