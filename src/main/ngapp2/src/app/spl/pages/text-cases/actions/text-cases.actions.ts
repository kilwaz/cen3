import {Action} from '@ngrx/store';

export enum TextCasesActionTypes {
  ProcessText = '[TextCases-ProcessText] Action',
  TextToProcessUpdated = '[TextCases-TextToProcessUpdated] Action',
  TextResultUpdated = '[TextCases-TextResultUpdated Action'
}

export class TextToProcessUpdated implements Action {
  readonly type = TextCasesActionTypes.TextToProcessUpdated;

  constructor(public payload: {
    textToProcess: string
  }) {
  }
}

export class TextResultUpdated implements Action {
  readonly type = TextCasesActionTypes.TextResultUpdated;

  constructor(public payload: {
    textResult: string
  }) {
  }
}

export class ProcessText implements Action {
  readonly type = TextCasesActionTypes.ProcessText;

  constructor(public payload: {
    textFunction: number
  }) {
  }
}

export type TextCasesActions =
  TextResultUpdated |
  ProcessText |
  TextToProcessUpdated;
