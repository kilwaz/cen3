import {Component, OnInit} from '@angular/core';
import {WebSocketService} from "../services/websocket.service";
import {CalculateSum} from "../wsActions/calculateSum";
import {Node} from "../wsObjects/node";
import {Formula} from "../wsObjects/formula";

@Component({
  selector: 'app-clarity',
  templateUrl: './clarity.component.html',
  styleUrls: ['./clarity.component.css']
})
export class ClarityComponent implements OnInit {
  webSocketService: WebSocketService;
  answer: number = 0;
  formulaA: string = "1+2";
  formulaB: string = "1+[A]";
  rootNode: Node;
  formula: Formula;

  constructor(private webSocketServiceConst: WebSocketService) {
    this.webSocketService = webSocketServiceConst;
  }

  ngOnInit() {
  }

  onKeyUp(formula: string, name: string) {
    console.log(formula + " - " + name);
    if (name == 'A') {
      this.formulaA = formula;
    } else if (name == 'B') {
      this.formulaB = formula;
    }
  }

  pushed() {
    let calculateSum: CalculateSum = new CalculateSum();
    let _this: ClarityComponent = this;

    calculateSum.formulaToProcess = this.formulaA;

    this.webSocketService.sendCallback(calculateSum, function (responseMessage) {
      let calculateSumResponse: CalculateSum = <CalculateSum>responseMessage;
      _this.answer = calculateSumResponse.result;
      _this.formula = calculateSumResponse.formula;
    });
  }
}
