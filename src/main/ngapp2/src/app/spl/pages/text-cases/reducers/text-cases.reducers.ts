// Actions
import {TextCasesActions, TextCasesActionTypes} from '../actions/text-cases.actions';

export interface TextCasesState {
  textToProcess: string;
  textResult: string;
  textFunction: number;
  fileToUpload: File;
}

export const initialAuthState: TextCasesState = {
  textToProcess: '',
  textResult: '',
  textFunction: 0,
  fileToUpload: null
};

export function textCasesReducer(state = initialAuthState, action: TextCasesActions): TextCasesState {
  switch (action.type) {
    case TextCasesActionTypes.TextResultUpdated: {
      const textResultPayload: string = action.payload.textResult;

      return {
        ...state,
        textResult: textResultPayload
      };
    }
    case TextCasesActionTypes.TextToProcessUpdated: {
      const textToProcessPayload: string = action.payload.textToProcess;

      return {
        ...state,
        textToProcess: textToProcessPayload,
      };
    }
    case TextCasesActionTypes.ProcessText: {
      const textFunctionPayload: number = action.payload.textFunction;

      return {
        ...state,
        textFunction: textFunctionPayload,
      };
    }
    case TextCasesActionTypes.FilePicked: {
      const fileToUploadPayload: File = action.payload.fileToUpload;

      return {
        ...state,
        fileToUpload: fileToUploadPayload,
      };
    }
    default:
      return state;
  }
}
