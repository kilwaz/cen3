import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {WebSocketService} from '../../../../../spl/services/websocket.service';
import {Menu} from "../../../../../spl/wsActions/menu";

@Injectable()
export class SideBarService {
  constructor(private webSocketService: WebSocketService) {
  }

  requestMenuLayout(): Observable<Menu> {
    const menuAction: Menu = new Menu();

    return this.webSocketService.sendWithObservable(menuAction) as Observable<Menu>;
  }
}
