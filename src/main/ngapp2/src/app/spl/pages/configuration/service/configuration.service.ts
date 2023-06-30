import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {WebSocketService} from '../../../services/websocket.service';
import {RequestRecordDefinitions} from "../../../wsActions/requestRecordDefinitions";
import {RequestDefinitions} from "../../../wsActions/requestDefinitions";
import {ChangeDefinition} from "../../../wsActions/changeDefinition";
import {DefinitionDataItem} from "../../../wsObjects/definitionDataItem";

@Injectable()
export class ConfigurationService {
  constructor(private webSocketService: WebSocketService) {
  }

  requestRecordDefinitions(): Observable<RequestRecordDefinitions> {
    const requestRecordDefinitionsAction: RequestRecordDefinitions = new RequestRecordDefinitions();
    return this.webSocketService.sendWithObservable(requestRecordDefinitionsAction) as Observable<RequestRecordDefinitions>;
  }

  requestDefinitions(recordDefinitionName: string): Observable<RequestDefinitions> {
    const requestDefinitionsAction: RequestDefinitions = new RequestDefinitions();
    requestDefinitionsAction.requestedRecordDefinitionName = recordDefinitionName;
    return this.webSocketService.sendWithObservable(requestDefinitionsAction) as Observable<RequestDefinitions>;
  }

  saveDefinition(definition: DefinitionDataItem): Observable<ChangeDefinition> {
    const changeDefinitionAction: ChangeDefinition = new ChangeDefinition();

    changeDefinitionAction.definition = definition;

    return this.webSocketService.sendWithObservable(changeDefinitionAction) as Observable<ChangeDefinition>;
  }
}
