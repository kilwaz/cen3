/*
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataitems.Definition
DO NOT MANUALLY CHANGE THIS FILE
*/


export class Definition {
	private _uuid: string;
	private _expression: string;
	private _name: string;

	constructor() {
	}

	wsFill(webSocketReference: any) : Definition {
		this._uuid = webSocketReference.uuid != undefined ? webSocketReference.uuid : this._uuid;
		this._expression = webSocketReference.expression != undefined ? webSocketReference.expression : this._expression;
		this._name = webSocketReference.name != undefined ? webSocketReference.name : this._name;
		return this;
	}

	get uuid(): string {
		return this._uuid;
	}

	get expression(): string {
		return this._expression;
	}

	get name(): string {
		return this._name;
	}

	set uuid(value: string) {
		this._uuid = value;
	}

	set expression(value: string) {
		this._expression = value;
	}

	set name(value: string) {
		this._name = value;
	}

}
