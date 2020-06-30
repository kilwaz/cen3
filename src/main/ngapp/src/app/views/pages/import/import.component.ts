import {Component, OnInit} from '@angular/core';
import {WebSocketService} from '../../../services/websocket.service';
import {Node} from '../../../wsObjects/node';
import {Formula} from '../../../wsObjects/formula';

import {FlatTreeControl} from '@angular/cdk/tree';
import {MatTreeFlatDataSource, MatTreeFlattener} from '@angular/material/tree';
import {TreeNode} from '../../../treeNode';
import {FormulaCheck} from '../../../wsActions/formulaCheck';

/** Flat node with expandable and level information */
interface ExampleFlatNode {
  expandable: boolean;
  name: string;
  level: number;
}

@Component({
  selector: 'app-import',
  templateUrl: './import.component.html',
  styleUrls: ['./import.component.scss']
})
export class ImportComponent implements OnInit {
  webSocketService: WebSocketService;
  answer = '-';
  formulaName = '';
  rootNode: Node;
  formula: Formula;
  treeFlattener;
  treeControl;
  dataSource;
  hasChild;

  constructor(private webSocketServiceConst: WebSocketService) {
    this.webSocketService = webSocketServiceConst;
  }

  private _transformer = (node: TreeNode, level: number) => {
    return {
      expandable: !!node.treeNodes && node.treeNodes.length > 0,
      name: node.name,
      level,
    };
  };

  ngOnInit() {
    this.treeFlattener = new MatTreeFlattener(this._transformer, node => node.level, node => node.expandable, node => node.treeNodes);
    this.treeControl = new FlatTreeControl<ExampleFlatNode>(node => node.level, node => node.expandable);
    this.dataSource = new MatTreeFlatDataSource(this.treeControl, this.treeFlattener);
    this.hasChild = (_: number, node: ExampleFlatNode) => node.expandable;
  }

  onKeyUp(formula: string, name: string) {
    if (name === 'Name') {
      this.formulaName = formula;
    }
  }

  checkFormula() {
    console.log('Checking the formula: ' + this.formulaName);
    const formulaCheck: FormulaCheck = new FormulaCheck();
    const _this: ImportComponent = this;
    formulaCheck.formulaToCheck = this.formulaName;

    this.webSocketService.sendCallback(formulaCheck, responseMessage => {
      const formulaCheckResponse: FormulaCheck = responseMessage as FormulaCheck;
      _this.answer = formulaCheckResponse.result;
      _this.formula = formulaCheckResponse.formula;
      _this.dataSource.data = [_this.buildTree(_this.formula.rootNode)];
    });
  }

  buildTree(node: Node): TreeNode {
    const treeNode: TreeNode = new TreeNode();

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
