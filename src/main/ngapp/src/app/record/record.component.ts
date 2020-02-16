import {Component, OnInit} from '@angular/core';
import {WebSocketService} from "../services/websocket.service";
import {Search} from "../wsActions/search";
import {Record} from "../wsObjects/record";
import {DataQuery} from "../wsActions/dataQuery";
import {Entry} from "../wsObjects/entry";

@Component({
  selector: 'app-record',
  templateUrl: './record.component.html',
  styleUrls: ['./record.component.css']
})
export class RecordComponent implements OnInit {
  webSocketService: WebSocketService;

  result: string;
  searchItem: string = "item";
  searchValue: string = "value";

  searchResults: Array<Record>;

  constructor(private webSocketServiceConst: WebSocketService) {
    this.webSocketService = webSocketServiceConst;
  }

  ngOnInit(): void {
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
        _this.searchResults.forEach(function (record) {
          if (record.uuid == entry.recordUUID) {
            if (record.entries === undefined) {
              record.entries = [];
            }
            record.entries.push(entry);
          }
        });
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

      _this.searchResults = searchResponse.searchResults;
    });
  }
}
