import {Component, OnInit} from '@angular/core';
import {WebSocketService} from "../services/websocket.service";
import {CalculateSum} from "../wsActions/calculateSum";

@Component({
  selector: 'app-clarity',
  templateUrl: './clarity.component.html',
  styleUrls: ['./clarity.component.css']
})
export class ClarityComponent implements OnInit {
  webSocketService: WebSocketService;
  answer: number = 0;
  formula: string = "1 + 2";

  constructor(private webSocketServiceConst: WebSocketService) {
    this.webSocketService = webSocketServiceConst;
  }

  ngOnInit() {
  }

  onEnter(formula: string) {
    this.formula = formula;
  }

  pushed() {
    let calculateSum: CalculateSum = new CalculateSum();
    let _this: ClarityComponent = this;

    calculateSum.formula = this.formula;

    this.webSocketService.sendCallback(calculateSum, function (responseMessage) {
      let calculateSumResponse: CalculateSum = <CalculateSum>responseMessage;
      _this.answer = calculateSumResponse.result;
    });
  }
}
