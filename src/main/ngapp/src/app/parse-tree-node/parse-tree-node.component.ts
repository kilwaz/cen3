import {Component, Input, OnInit} from '@angular/core';
import {Node} from "../wsObjects/node";

@Component({
  selector: 'app-parse-tree-node',
  templateUrl: './parse-tree-node.component.html',
  styleUrls: ['./parse-tree-node.component.css']
})
export class ParseTreeNodeComponent implements OnInit {
  @Input() node: Node;

  constructor() { }

  ngOnInit() {
  }
}
