import {Message} from "./message";

export class CountDownTrigger extends Message {
	
	constructor() {
		super();
		this.type = "CountDownTrigger";
	}

	decodeResponse(msgRaw: any) {
	}

}