import {Component, OnInit} from '@angular/core';
import {WebSocketService} from "../services/websocket.service";
import {DataQuery} from "../wsActions/dataQuery";

@Component({
  selector: 'app-record',
  templateUrl: './record.component.html',
  styleUrls: ['./record.component.css']
})
export class RecordComponent implements OnInit {
  webSocketService: WebSocketService;

  result: string;

  constructor(private webSocketServiceConst: WebSocketService) {
    this.webSocketService = webSocketServiceConst;
  }

  ngOnInit(): void {
    let dataQuery: DataQuery = new DataQuery();
    dataQuery.recordToCheck = "Num";
    let _this: RecordComponent = this;

    this.webSocketService.sendCallback(dataQuery, function (responseMessage) {
      let dataQueryResponse: DataQuery = <DataQuery>responseMessage;
      _this.result = dataQueryResponse.entry.value;
    });
  }
}
