import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {WebSocketService} from '../../../services/websocket.service';
import {ToolTest} from '../../../wsActions/toolTest';

@Injectable()
export class ToolService {
  constructor(private webSocketService: WebSocketService) {
  }

  test(startDay: number, startMonth: number, startYear: number, ageOnDay: number, ageOnMonth: number, ageOnYear: number): Observable<ToolTest> {
    const toolTestAction: ToolTest = new ToolTest();
    toolTestAction.startDay = startDay;
    toolTestAction.startMonth = startMonth;
    toolTestAction.startYear = startYear;
    
    toolTestAction.ageOnDay = ageOnDay;
    toolTestAction.ageOnMonth = ageOnMonth;
    toolTestAction.ageOnYear = ageOnYear;

    return this.webSocketService.sendWithObservable(toolTestAction) as Observable<ToolTest>;
  }
}
