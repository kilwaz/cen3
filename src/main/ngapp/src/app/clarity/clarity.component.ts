import {Component, OnInit} from '@angular/core';
import {WebSocketService} from "../services/websocket.service";
import {Node} from "../wsObjects/node";
import {Formula} from "../wsObjects/formula";

import {FlatTreeControl} from '@angular/cdk/tree';
import {MatTreeFlatDataSource, MatTreeFlattener} from '@angular/material/tree';
import {FormulaCheck} from "../wsActions/formulaCheck";
import {TreeNode} from "../treeNode";

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
  answer: string = "-";
  formulaName: string = "";
  rootNode: Node;
  formula: Formula;

  constructor(private webSocketServiceConst: WebSocketService) {
    this.webSocketService = webSocketServiceConst;
  }

  private _transformer = (node: TreeNode, level: number) => {
    return {
      expandable: !!node.treeNodes && node.treeNodes.length > 0,
      name: node.name,
      level: level,
    };
  };

  treeControl = new FlatTreeControl<ExampleFlatNode>(node => node.level, node => node.expandable);
  treeFlattener = new MatTreeFlattener(this._transformer, node => node.level, node => node.expandable, node => node.treeNodes);
  dataSource = new MatTreeFlatDataSource(this.treeControl, this.treeFlattener);

  hasChild = (_: number, node: ExampleFlatNode) => node.expandable;

  ngOnInit() {
  }

  onKeyUp(formula: string, name: string) {
    if (name == 'Name') {
      this.formulaName = formula;
    }
  }

  checkFormula() {
    console.log("Checking the formula: " + this.formulaName);
    let formulaCheck: FormulaCheck = new FormulaCheck();
    let _this: ClarityComponent = this;
    formulaCheck.formulaToCheck = this.formulaName;

    this.webSocketService.sendCallback(formulaCheck, function (responseMessage) {
      let formulaCheckResponse: FormulaCheck = <FormulaCheck>responseMessage;
      _this.answer = formulaCheckResponse.result;
      _this.formula = formulaCheckResponse.formula;
      _this.dataSource.data = [_this.buildTree(_this.formula.rootNode)];
    });
  }

  buildTree(node: Node): TreeNode {
    let treeNode: TreeNode = new TreeNode();

    treeNode.name = node.value;

    if (node.left != null) {
      treeNode.addToList(this.buildTree(node.left));
    }
    if (node.right != null) {
      treeNode.addToList(this.buildTree(node.right));
    }
    if (node.nodeList != null) {
      node.nodeList.forEach((element) => {
        treeNode.addToList(this.buildTree(element));
      });
    }

    return treeNode;
  }
}
