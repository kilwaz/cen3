import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {WebSocketService} from '../../../services/websocket.service';
import {Management} from "../../../wsActions/management";

@Injectable()
export class ManagementService {
  constructor(private webSocketService: WebSocketService) {
  }

  queryManagement(): Observable<Management> {
    const managementAction: Management = new Management();

    return this.webSocketService.sendWithObservable(managementAction) as Observable<Management>;
  }
}
