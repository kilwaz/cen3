import {Component, OnInit} from '@angular/core';
import {WebSocketService} from "../services/websocket.service";
import {Search} from "../wsActions/search";

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

  search(): void {
    let search: Search = new Search();
    search.searchItem = this.searchItem;
    search.searchValue = this.searchValue;
    let _this: RecordComponent = this;

    this.webSocketService.sendCallback(search, function (responseMessage) {
      let searchResponse: Search = <Search>responseMessage;

    });
  }
}
