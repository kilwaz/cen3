import {Action} from '@ngrx/store';

export enum TextCasesActionTypes {
  ProcessText = '[TextCases-ProcessText] Action',
  TextToProcessUpdated = '[TextCases-TextToProcessUpdated] Action',
  TextResultUpdated = '[TextCases-TextResultUpdated Action',
  FilePicked = '[TextCases-FilePicked Action',
  UploadFileBegin = '[TextCases-UploadFile Action'
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

export class UploadFileBegin implements Action {
  readonly type = TextCasesActionTypes.UploadFileBegin;

  constructor(public payload: {}) {
  }
}

export class FilePicked implements Action {
  readonly type = TextCasesActionTypes.FilePicked;

  constructor(public payload: {
    fileToUpload: File
  }) {
  }
}

export type TextCasesActions =
  TextResultUpdated |
  ProcessText |
  TextToProcessUpdated |
  UploadFileBegin |
  FilePicked;
