// Actions
import {ToolActions, ToolActionTypes} from '../actions/tool.actions';

export interface ToolState {
  ageDays: number;
  ageMonths: number;
  ageYears: number;
  startDate: Date;
  ageOnDate: Date;
}

export const initialAuthState: ToolState = {
  ageDays: 0,
  ageMonths: 0,
  ageYears: 0,
  startDate: null,
  ageOnDate: null
};

export function toolReducer(state = initialAuthState, action: ToolActions): ToolState {
  switch (action.type) {
    case ToolActionTypes.Test: {
      const ageDaysPayload: number = action.payload.ageDays;
      const ageMonthsPayload: number = action.payload.ageMonths;
      const ageYearsPayload: number = action.payload.ageYears;

      return {
        ...state,
        ageDays: ageDaysPayload,
        ageMonths: ageMonthsPayload,
        ageYears: ageYearsPayload
      };
    }
    case ToolActionTypes.StartDateChange: {
      const startDatePayload: Date = action.payload.startDate;

      return {
        ...state,
        startDate: startDatePayload,
      };
    }
    case ToolActionTypes.AgeOnDateChange: {
      const ageOnDatePayload: Date = action.payload.ageOnDate;

      return {
        ...state,
        ageOnDate: ageOnDatePayload,
      };
    }
    default:
      return state;
  }
}
