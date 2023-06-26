import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {WebSocketService} from '../../../services/websocket.service';
import {Worksheet} from "../../../wsActions/worksheet";
import {FilteredList} from "../../../wsActions/filteredList";
import {SortFilter} from "../../../wsObjects/sortFilter";
import {WorksheetStatus} from "../../../wsObjects/worksheetStatus";

@Injectable()
export class WorksheetService {
  constructor(private webSocketService: WebSocketService) {
  }

  worksheetRequest(sortFilter: SortFilter, worksheetId: string, worksheetStatus: WorksheetStatus): Observable<Worksheet> {
    const worksheet: Worksheet = new Worksheet();
    worksheet.sortFilter = sortFilter;
    worksheet.requestID = worksheetId;
    worksheet.worksheetStatus = worksheetStatus;

    return this.webSocketService.sendWithObservable(worksheet) as Observable<Worksheet>;
  }

  filteredListRequest(definition: string): Observable<FilteredList> {
    const filteredList: FilteredList = new FilteredList();
    filteredList.definition = definition;

    return this.webSocketService.sendWithObservable(filteredList) as Observable<FilteredList>;
  }
}
