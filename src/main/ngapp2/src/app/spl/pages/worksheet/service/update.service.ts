import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {WebSocketService} from '../../../services/websocket.service';
import {Update} from "../../../wsActions/update";

@Injectable()
export class UpdateService {
  constructor(private webSocketService: WebSocketService) {
  }

  update(value: string, definitionName: string, recordUUID: string, updateSource: string): Observable<Update> {
    const update: Update = new Update();
    update.value = value;
    update.definitionName = definitionName;
    update.recordUUID = recordUUID;
    update.updateSource = updateSource;

    return this.webSocketService.sendWithObservable(update) as Observable<Update>;
  }
}
