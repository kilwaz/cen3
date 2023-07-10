import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {WebSocketService} from '../../../services/websocket.service';
import {RequestRecordDefinitions} from "../../../wsActions/requestRecordDefinitions";
import {RequestDefinitions} from "../../../wsActions/requestDefinitions";
import {ChangeDefinition} from "../../../wsActions/changeDefinition";
import {DefinitionDataItem} from "../../../wsObjects/definitionDataItem";
import {RequestFormulaContexts} from "../../../wsActions/requestFormulaContexts";
import {RequestWorksheetConfigs} from "../../../wsActions/requestWorksheetConfigs";
import {UpdateWorksheetConfig} from "../../../wsActions/updateWorksheetConfig";

@Injectable()
export class ConfigurationService {
  constructor(private webSocketService: WebSocketService) {
  }

  requestRecordDefinitions(): Observable<RequestRecordDefinitions> {
    const requestRecordDefinitionsAction: RequestRecordDefinitions = new RequestRecordDefinitions();
    return this.webSocketService.sendWithObservable(requestRecordDefinitionsAction) as Observable<RequestRecordDefinitions>;
  }

  requestFormulaContexts(): Observable<RequestFormulaContexts> {
    const requestFormulaContextsAction: RequestFormulaContexts = new RequestFormulaContexts();
    return this.webSocketService.sendWithObservable(requestFormulaContextsAction) as Observable<RequestFormulaContexts>;
  }

  requestDefinitions(name: string, type: string): Observable<RequestDefinitions> {
    const requestDefinitionsAction: RequestDefinitions = new RequestDefinitions();
    if (type == 'Record') {
      requestDefinitionsAction.requestedRecordDefinitionName = name;
    } else if (type == 'FormulaContext') {
      requestDefinitionsAction.requestedFormulaContextName = name;
    }

    return this.webSocketService.sendWithObservable(requestDefinitionsAction) as Observable<RequestDefinitions>;
  }

  requestAllDefinitions(): Observable<RequestDefinitions> {
    const requestDefinitionsAction: RequestDefinitions = new RequestDefinitions();
    requestDefinitionsAction.requestedRecordDefinitionName = 'All';
    return this.webSocketService.sendWithObservable(requestDefinitionsAction) as Observable<RequestDefinitions>;
  }

  requestWorksheetConfigs(): Observable<RequestWorksheetConfigs> {
    const requestWorksheetConfigAction: RequestWorksheetConfigs = new RequestWorksheetConfigs();
    return this.webSocketService.sendWithObservable(requestWorksheetConfigAction) as Observable<RequestWorksheetConfigs>;
  }

  saveDefinition(definition: DefinitionDataItem): Observable<ChangeDefinition> {
    const changeDefinitionAction: ChangeDefinition = new ChangeDefinition();

    changeDefinitionAction.definition = definition;

    return this.webSocketService.sendWithObservable(changeDefinitionAction) as Observable<ChangeDefinition>;
  }

  updateWorksheetConfig(worksheetConfigName: string, definitionId: string): Observable<UpdateWorksheetConfig> {
    const changeDefinitionAction: UpdateWorksheetConfig = new UpdateWorksheetConfig();

    changeDefinitionAction.worksheetConfigName = worksheetConfigName;
    changeDefinitionAction.definitionId = definitionId;

    return this.webSocketService.sendWithObservable(changeDefinitionAction) as Observable<UpdateWorksheetConfig>;
  }
}
