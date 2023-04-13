import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {WebSocketService} from '../../../services/websocket.service';
import {TextCases} from '../../../wsActions/textCases';
import {FileUpload} from "../../../wsActions/fileUpload";
import {tap} from "rxjs/operators";
import {TextResultUpdated} from "../actions/text-cases.actions";

@Injectable()
export class TextCasesService {
  constructor(private webSocketService: WebSocketService) {
  }

  textFunction(textToProcess: string, textFunction: number): Observable<TextCases> {
    const textCasesAction: TextCases = new TextCases();
    textCasesAction.textToProcess = textToProcess;
    textCasesAction.textFunction = textFunction;

    return this.webSocketService.sendWithObservable(textCasesAction) as Observable<TextCases>;
  }

  sendTestFile(file: File): void {
    file.arrayBuffer().then((buffer) => {
      const fileUploadAction: FileUpload = new FileUpload();
      fileUploadAction.fileName = file.name;
      fileUploadAction.fileType = file.type;
      fileUploadAction.fileSize = file.size;

      // Send request to server to prepare to receive a file
      let fileUploadRequest$ = this.webSocketService.sendWithObservable(fileUploadAction) as Observable<FileUpload>;
      // Response from server
      fileUploadRequest$.pipe(
        tap(result => {
          const fullFileArray = new Uint8Array(buffer);

          for (let frame = 0; frame < result.numberOfPieces; frame++) {
            let referenceBytes = new Uint8Array([frame % 254, Math.floor(frame / 254), result.fileReference, 0, 0, 0, 0, 0]);

            // Create frame size
            let frameArray;
            if (frame !== result.numberOfPieces - 1) {
              frameArray = new Uint8Array(referenceBytes.length + result.frameSize);
            } else { // Last frame size needs to be treated differently due to partial frame size
              frameArray = new Uint8Array(referenceBytes.length + fullFileArray.length - ((result.frameSize * frame)));
            }
            // Reference bytes always sit at the start of the frame
            frameArray.set(referenceBytes);

            // Create the frame file contents
            let frameFileArray;
            if (frame !== result.numberOfPieces - 1) {
              frameFileArray = fullFileArray.slice(result.frameSize * frame, result.frameSize * (frame + 1));
            } else { // Last frame size needs to be treated differently due to partial frame size
              frameFileArray = fullFileArray.slice(result.frameSize * frame, fullFileArray.length);
            }
            // Join the frame file contents onto the end of frame
            frameArray.set(frameFileArray, referenceBytes.length);

            // Send the file contents with attached reference bytes at the start of each frame
            this.webSocketService.sendBinaryData(frameArray);
          }
        }),
      ).subscribe();
    });
  }
}
