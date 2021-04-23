import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {WebSocketService} from '../../../services/websocket.service';
import {ToolTest} from '../../../wsActions/toolTest';

@Injectable()
export class ToolService {
  constructor(private webSocketService: WebSocketService) {
  }

  test(message: string): Observable<ToolTest> {
    const toolTestAction: ToolTest = new ToolTest();
    toolTestAction.message = message;

    return this.webSocketService.sendWithObservable(toolTestAction) as Observable<ToolTest>;
  }
}
