import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {WebSocketService} from '../../../services/websocket.service';
import {Worksheet} from "../../../wsActions/worksheet";
import {FilteredList} from "../../../wsActions/filteredList";
import {SortFilterDataItem} from "../../../wsObjects/sortFilterDataItem";
import {WorksheetStatusDataItem} from "../../../wsObjects/worksheetStatusDataItem";

@Injectable()
export class WorksheetService {
  constructor(private webSocketService: WebSocketService) {
  }

  worksheetRequest(sortFilter: SortFilterDataItem, nodeReference: string, worksheetStatus: WorksheetStatusDataItem): Observable<Worksheet> {
    const worksheet: Worksheet = new Worksheet();
    worksheet.sortFilter = sortFilter;
    worksheet.nodeReference = nodeReference;
    worksheet.worksheetStatus = worksheetStatus;

    return this.webSocketService.sendWithObservable(worksheet) as Observable<Worksheet>;
  }

  filteredListRequest(definition: string, nodeReference: string): Observable<FilteredList> {
    const filteredList: FilteredList = new FilteredList();
    filteredList.definition = definition;
    filteredList.nodeReference = nodeReference

    return this.webSocketService.sendWithObservable(filteredList) as Observable<FilteredList>;
  }
}
