/* 
THIS FILE IS AUTO GENERATED FROM THE SOURCE FILE:
requests.spark.websockets.objects.messages.dataobjects.ToolTestData
DO NOT MANUALLY CHANGE THIS FILE
*/

import {Message} from "./message";

export class ToolTest extends Message {
	private _startYear: number;
	private _startMonth: number;
	private _startDay: number;
	private _ageOnYear: number;
	private _ageOnMonth: number;
	private _ageOnDay: number;
	private _ageYears: number;
	private _ageMonths: number;
	private _ageDays: number;
	
	constructor() {
		super();
		this.type = "ToolTest";
	}

	decodeResponse(msgRaw: any) {
		this._ageYears = msgRaw.ageYears;
		this._ageMonths = msgRaw.ageMonths;
		this._ageDays = msgRaw.ageDays;
	}

	get startYear(): number {
		return this._startYear;
	}
	
	get startMonth(): number {
		return this._startMonth;
	}
	
	get startDay(): number {
		return this._startDay;
	}
	
	get ageOnYear(): number {
		return this._ageOnYear;
	}
	
	get ageOnMonth(): number {
		return this._ageOnMonth;
	}
	
	get ageOnDay(): number {
		return this._ageOnDay;
	}
	
	get ageYears(): number {
		return this._ageYears;
	}
	
	get ageMonths(): number {
		return this._ageMonths;
	}
	
	get ageDays(): number {
		return this._ageDays;
	}
	
	set startYear(value: number) {
		this._startYear = value;
	}
	
	set startMonth(value: number) {
		this._startMonth = value;
	}
	
	set startDay(value: number) {
		this._startDay = value;
	}
	
	set ageOnYear(value: number) {
		this._ageOnYear = value;
	}
	
	set ageOnMonth(value: number) {
		this._ageOnMonth = value;
	}
	
	set ageOnDay(value: number) {
		this._ageOnDay = value;
	}
	
	set ageYears(value: number) {
		this._ageYears = value;
	}
	
	set ageMonths(value: number) {
		this._ageMonths = value;
	}
	
	set ageDays(value: number) {
		this._ageDays = value;
	}
	
}