// Actions
import {ManagementActions, ManagementActionTypes} from '../actions/management.actions';

export interface ManagementState {
  totalMemory: number,
  freeMemory: number,
  maxMemory: number,

  fileData: Blob,
  fileUrl: string
}

export const initialAuthState: ManagementState = {
  totalMemory: 0,
  freeMemory: 0,
  maxMemory: 0,

  fileData: null,
  fileUrl: null
};

export function managementReducer(state = initialAuthState, action: ManagementActions): ManagementState {
  switch (action.type) {
    case ManagementActionTypes.ProcessResults: {
      return {
        ...state,
        totalMemory: action.payload.totalMemory,
        freeMemory: action.payload.freeMemory,
        maxMemory: action.payload.maxMemory
      };
    }
    case ManagementActionTypes.ProcessDownloadResults: {
      return {
        ...state,
        fileData: action.payload.fileData
      };
    }
    case ManagementActionTypes.UpdateFileURL: {
      return {
        ...state,
        fileUrl: action.payload.fileUrl
      };
    }
    default:
      return state;
  }
}
