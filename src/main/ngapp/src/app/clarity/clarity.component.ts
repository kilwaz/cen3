import {Component, OnInit} from '@angular/core';
import {WebSocketService} from "../services/websocket.service";
import {CalculateSum} from "../wsActions/calculateSum";
import {Node} from "../wsObjects/node";
import {Formula} from "../wsObjects/formula";

import {FlatTreeControl} from '@angular/cdk/tree';
import {MatTreeFlatDataSource, MatTreeFlattener} from '@angular/material/tree';


import {MatIconRegistry} from '@angular/material/icon';
import {DomSanitizer} from "@angular/platform-browser";


/**
 * Food data with nested structure.
 * Each node has a name and an optiona list of children.
 */
interface FoodNode {
  name: string;
  children?: FoodNode[];
}
const TREE_DATA: FoodNode[] = [
  {
    name: 'Fruit',
    children: [
      {name: 'Apple'},
      {name: 'Banana'},
      {name: 'Fruit loops'},
    ]
  }, {
    name: 'Vegetables',
    children: [
      {
        name: 'Green',
        children: [
          {name: 'Broccoli'},
          {name: 'Brussel sprouts'},
        ]
      }, {
        name: 'Orange',
        children: [
          {name: 'Pumpkins'},
          {name: 'Carrots'},
        ]
      },
    ]
  },
];

/** Flat node with expandable and level information */
interface ExampleFlatNode {
  expandable: boolean;
  name: string;
  level: number;
}


@Component({
  selector: 'app-clarity',
  templateUrl: './clarity.component.html',
  styleUrls: ['./clarity.component.css']
})
export class ClarityComponent implements OnInit {
  webSocketService: WebSocketService;
  answer: number = 0;
  formulaName: string = "err hello?";
  rootNode: Node;
  formula: Formula;

  constructor(private webSocketServiceConst: WebSocketService, iconRegistry: MatIconRegistry, sanitizer: DomSanitizer) {
    this.webSocketService = webSocketServiceConst;


    this.dataSource.data = TREE_DATA;

    iconRegistry.addSvgIcon(
      'thumbs-up',
      sanitizer.bypassSecurityTrustResourceUrl('assets/img/examples/thumbup-icon.svg'));

  }


  private _transformer = (node: FoodNode, level: number) => {
    return {
      expandable: !!node.children && node.children.length > 0,
      name: node.name,
      level: level,
    };
  };

  treeControl = new FlatTreeControl<ExampleFlatNode>(
    node => node.level, node => node.expandable);

  treeFlattener = new MatTreeFlattener(
    this._transformer, node => node.level, node => node.expandable, node => node.children);

  dataSource = new MatTreeFlatDataSource(this.treeControl, this.treeFlattener);

  hasChild = (_: number, node: ExampleFlatNode) => node.expandable;









  ngOnInit() {
  }

  // onKeyUp(formula: string, name: string) {
  //   console.log(formula + " - " + name);
  //   if (name == 'A') {
  //     this.formulaA = formula;
  //   } else if (name == 'B') {
  //     this.formulaB = formula;
  //   }
  // }

  onKeyUp(formula: string, name: string) {
    // console.log(formula + " - " + name);
    if (name == 'Name') {
      this.formulaName = formula;
    }
  }

  checkFormula() {
    console.log("Checking the formula: " + this.formulaName);
    // let calculateSum: CalculateSum = new CalculateSum();
    // let _this: ClarityComponent = this;
    //
    // calculateSum.formulaToProcess = this.formulaA;
    //
    // this.webSocketService.sendCallback(calculateSum, function (responseMessage) {
    //   let calculateSumResponse: CalculateSum = <CalculateSum>responseMessage;
    //   _this.answer = calculateSumResponse.result;
    //   _this.formula = calculateSumResponse.formula;
    // });
  }

  // pushed() {
  //   let calculateSum: CalculateSum = new CalculateSum();
  //   let _this: ClarityComponent = this;
  //
  //   calculateSum.formulaToProcess = this.formulaA;
  //
  //   this.webSocketService.sendCallback(calculateSum, function (responseMessage) {
  //     let calculateSumResponse: CalculateSum = <CalculateSum>responseMessage;
  //     _this.answer = calculateSumResponse.result;
  //     _this.formula = calculateSumResponse.formula;
  //   });
  // }
}
