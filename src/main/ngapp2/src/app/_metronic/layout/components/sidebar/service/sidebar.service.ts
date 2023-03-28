import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {WebSocketService} from '../../../../../spl/services/websocket.service';
import {Role} from "../../../../../spl/wsActions/role";

@Injectable()
export class SideBarService {
  constructor(private webSocketService: WebSocketService) {
  }

  textFunction(): Observable<Role> {
    const roleAction: Role = new Role();
    roleAction.username = "err hello? role";

    return this.webSocketService.sendWithObservable(roleAction) as Observable<Role>;
  }
}
