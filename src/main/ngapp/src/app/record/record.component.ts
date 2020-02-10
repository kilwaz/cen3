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



  constructor(private webSocketServiceConst: WebSocketService) {
    this.webSocketService = webSocketServiceConst;
  }

  ngOnInit(): void {
    let dataQuery: DataQuery = new DataQuery();
    let _this: RecordComponent = this;

    this.webSocketService.sendCallback(dataQuery, function (responseMessage) {
      let dataQueryResponse: DataQuery = <DataQuery>responseMessage;
      console.log(dataQueryResponse.result);
    });
  }
}
