import {Component, OnInit, ViewChild} from '@angular/core';
import {WebSocketService} from "../services/websocket.service";
import {Search} from "../wsActions/search";
import {DataQuery} from "../wsActions/dataQuery";
import {Entry} from "../wsObjects/entry";
import {RecordContainer} from "../containers/recordContainer";
import {MatTable} from "@angular/material/table";

@Component({
  selector: 'app-record',
  templateUrl: './record.component.html',
  styleUrls: ['./record.component.css']
})
export class RecordComponent implements OnInit {
  webSocketService: WebSocketService;

  displayedColumns: string[] = ['UUID','SUM'];

  @ViewChild(MatTable) matTable: MatTable<any>;

  result: string;
  searchItem: string = "item";
  searchValue: string = "value";

  searchResults: Array<RecordContainer>;

  constructor(private webSocketServiceConst: WebSocketService) {
    this.webSocketService = webSocketServiceConst;
  }

  ngOnInit(): void {
    this.searchResults = new Array<RecordContainer>();
  }

  inputChanges(value: string, name: string): void {
    if (name == 'searchItem') {
      this.searchItem = value;
    } else if (name == 'searchValue') {
      this.searchValue = value;
    }
  }

  load(uuid: string): void {
    let dataQuery: DataQuery = new DataQuery();
    let requestedEntries: Array<Entry> = new Array<Entry>();

    let entry: Entry = new Entry();
    entry.name = "Sum";
    requestedEntries.push(entry);

    dataQuery.requestedEntries = requestedEntries;
    dataQuery.recordToCheck = uuid;

    let _this: RecordComponent = this;

    this.webSocketService.sendCallback(dataQuery, function (responseMessage) {
      let dataQueryResponse: DataQuery = <DataQuery>responseMessage;

      dataQueryResponse.entries.forEach(function (entry) {
        _this.searchResults.forEach(function (recordContainer) {
          if (recordContainer.record.uuid == entry.recordUUID) {
            recordContainer.updateEntry(entry);
          }
        });
        debugger;
        _this.matTable.renderRows();
      });
    });
  }

  search(): void {
    let search: Search = new Search();
    search.searchItem = this.searchItem;
    search.searchValue = this.searchValue;
    let _this: RecordComponent = this;

    this.webSocketService.sendCallback(search, function (responseMessage) {
      let searchResponse: Search = <Search>responseMessage;

      searchResponse.searchResults.forEach(function (record) {
        _this.searchResults.push(new RecordContainer(record));
      });
    });
  }
}
