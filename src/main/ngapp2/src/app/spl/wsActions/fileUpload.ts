/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataobjects.FileUploadData
DO NOT MANUALLY CHANGE THIS FILE
*/

import {Message} from "./message";

export class FileUpload extends Message {
	private _fileName: string;
	private _fileType: string;
	private _fileSize: number;
	private _numberOfPieces: number;
	private _frameSize: number;
	private _fileReference: number;
	
	constructor() {
		super();
		this.type = "FileUpload";
	}

	decodeResponse(msgRaw: any) {
		this._numberOfPieces = msgRaw.numberOfPieces;
		this._frameSize = msgRaw.frameSize;
		this._fileReference = msgRaw.fileReference;
	}

	get fileName(): string {
		return this._fileName;
	}
	
	get fileType(): string {
		return this._fileType;
	}
	
	get fileSize(): number {
		return this._fileSize;
	}
	
	get numberOfPieces(): number {
		return this._numberOfPieces;
	}
	
	get frameSize(): number {
		return this._frameSize;
	}
	
	get fileReference(): number {
		return this._fileReference;
	}
	
	set fileName(value: string) {
		this._fileName = value;
	}
	
	set fileType(value: string) {
		this._fileType = value;
	}
	
	set fileSize(value: number) {
		this._fileSize = value;
	}
	
	set numberOfPieces(value: number) {
		this._numberOfPieces = value;
	}
	
	set frameSize(value: number) {
		this._frameSize = value;
	}
	
	set fileReference(value: number) {
		this._fileReference = value;
	}
	
}