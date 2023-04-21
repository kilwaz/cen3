import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {WebSocketService} from '../../../services/websocket.service';
import {Worksheet} from "../../../wsActions/worksheet";

@Injectable()
export class WorksheetService {
  constructor(private webSocketService: WebSocketService) {
  }

  testFunction(): Observable<Worksheet> {
    const worksheetAction: Worksheet = new Worksheet();
    worksheetAction.requestID = "10174";

    return this.webSocketService.sendWithObservable(worksheetAction) as Observable<Worksheet>;
  }
}
