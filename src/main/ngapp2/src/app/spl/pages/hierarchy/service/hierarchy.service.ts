import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {WebSocketService} from '../../../services/websocket.service';
import {Hierarchy} from "../../../wsActions/hierarchy";

@Injectable()
export class HierarchyService {
  constructor(private webSocketService: WebSocketService) {
  }

  requestHierarchy(): Observable<Hierarchy> {
    const hierarchyAction: Hierarchy = new Hierarchy();

    return this.webSocketService.sendWithObservable(hierarchyAction) as Observable<Hierarchy>;
  }
}
