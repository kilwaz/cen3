import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {WebSocketService} from '../../../services/websocket.service';
import {Worksheet} from "../../../wsActions/worksheet";
import {FilteredList} from "../../../wsActions/filteredList";

@Injectable()
export class WorksheetService {
  constructor(private webSocketService: WebSocketService) {
  }

  worksheetRequest(): Observable<Worksheet> {
    const worksheet: Worksheet = new Worksheet();

    return this.webSocketService.sendWithObservable(worksheet) as Observable<Worksheet>;
  }

  filteredListRequest(definition: string): Observable<FilteredList> {
    const filteredList: FilteredList = new FilteredList();
    filteredList.definition = definition;

    return this.webSocketService.sendWithObservable(filteredList) as Observable<FilteredList>;
  }
}
