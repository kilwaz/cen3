import {Component, OnInit} from '@angular/core';
import {WebSocketService} from "../../services/websocket.service";
import {DefinitionLookup} from "../../wsActions/definitionLookup";
import {Definition} from "../../wsObjects/definition";

@Component({
  selector: 'clarity-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {
  webSocketService: WebSocketService;
  definitionList: Array<Definition>;

  pickedDefinitionItem: Definition;

  constructor(private webSocketServiceConst: WebSocketService) {
    this.webSocketService = webSocketServiceConst;
  }

  ngOnInit(): void {
    let definitionLookup: DefinitionLookup = new DefinitionLookup();
    let _this: ListComponent = this;

    this.webSocketService.sendCallback(definitionLookup, function (responseMessage) {
      let definitionLookupResponse: DefinitionLookup = <DefinitionLookup>responseMessage;
      _this.definitionList = definitionLookupResponse.entries;
    });
  }

  pickedDefinition(definition: Definition): void {
    this.pickedDefinitionItem = definition;
  }
}
