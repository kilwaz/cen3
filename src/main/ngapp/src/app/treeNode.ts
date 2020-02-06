export class TreeNode {
  private _name: string;
  private _treeNodes: TreeNode[] = [];

  constructor() {
  }

  set name(value: string) {
    this._name = value;
  }

  addToList(treeNode: TreeNode) {
    this._treeNodes.push(treeNode);
  }

  get name(): string {
    return this._name;
  }

  get treeNodes(): TreeNode[] {
    return this._treeNodes;
  }
}
