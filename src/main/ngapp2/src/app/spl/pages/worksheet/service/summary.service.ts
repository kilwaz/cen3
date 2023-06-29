import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {WebSocketService} from "../../../services/websocket.service";
import {Summary} from "../../../wsActions/summary";

@Injectable()
export class SummaryService {
  constructor(private webSocketService: WebSocketService) {

  }

  loadSummary(worksheetId: string): Observable<Summary> {
    const summary: Summary = new Summary();
    summary.requestID = worksheetId;

    return this.webSocketService.sendWithObservable(summary) as Observable<Summary>;
  }
}
