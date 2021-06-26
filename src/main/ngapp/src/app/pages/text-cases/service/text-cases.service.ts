import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {WebSocketService} from '../../../services/websocket.service';
import {TextCases} from '../../../wsActions/textCases';

@Injectable()
export class TextCasesService {
  constructor(private webSocketService: WebSocketService) {
  }

  textFunction(textToProcess: string, textFunction: number): Observable<TextCases> {
    console.log('What the fuck? ' + textToProcess);
    const textCasesAction: TextCases = new TextCases();
    textCasesAction.textToProcess = textToProcess;
    textCasesAction.textFunction = textFunction;

    return this.webSocketService.sendWithObservable(textCasesAction) as Observable<TextCases>;
  }
}
