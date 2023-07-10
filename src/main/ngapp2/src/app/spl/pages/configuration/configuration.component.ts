import {AfterViewInit, Component, OnDestroy, OnInit} from '@angular/core';

// RxJS
import {Observable, Subscription} from 'rxjs';
import * as ace from "ace-builds";
import {Ace} from "ace-builds";
import 'ace-builds/src-noconflict/theme-monokai';
import 'ace-builds/src-noconflict/ext-language_tools';
import 'ace-builds/src-noconflict/mode-javascript';

// Store
import {select, Store} from '@ngrx/store';

// Selectors
import {ConfigurationService} from './service/configuration.service';
import {
  ConfigurationState,
  DefinitionsState,
  FormulaContextState,
  RecordDefinitionsState,
  WorksheetConfigsState
} from "./reducers/configuration.reducers";
import {
  AddNewWorksheetConfig,
  RequestDefinitions,
  RequestWorksheetConfigs,
  SaveDefinition,
  SelectDefinition,
  SelectType,
  SelectWorksheetConfig,
  UpdateDefinition, UpdateWorksheetConfig
} from "./actions/configuration.actions";
import {RecordDefinitionDataItem} from "../../wsObjects/recordDefinitionDataItem";
import {
  definitions,
  formulaContexts,
  recordDefinitions,
  selectedDefinition,
  selectedFormulaContext,
  selectedRecordDefinition,
  selectedType,
  selectedWorksheetConfig,
  worksheetConfigs
} from "./selectors/configuration.selectors";
import {selectAll as selectAllRecordDefinition} from "./selectors/recordDefinition.selectors";
import {selectAll as selectAllDefinition, selectYourEntityByIds} from "./selectors/definition.selectors";
import {selectAll as selectAllFormulaContext} from "./selectors/formulaContext.selectors";
import {selectAll as selectAllWorksheetConfig} from "./selectors/worksheetConfig.selectors";
import {DefinitionDataItem} from "../../wsObjects/definitionDataItem";
import {FormulaContextDataItem} from "../../wsObjects/formulaContextDataItem";
import {WorksheetConfigDataItem} from "../../wsObjects/worksheetConfigDataItem";
import {WebWorksheetConfigDataItem} from "../../wsObjects/webWorksheetConfigDataItem";
import Editor = Ace.Editor;
import {Scope} from "eslint";
import Definition = Scope.Definition;

// Action

@Component({
  selector: 'configuration',
  templateUrl: './configuration.component.html',
  styleUrls: ['./configuration.component.scss']
})
export class ConfigurationComponent implements OnInit, OnDestroy, AfterViewInit {
  definitions$: Observable<Array<DefinitionDataItem>>;
  definitionsState$: Observable<DefinitionsState>;

  definitionsForContext$: Observable<Array<DefinitionDataItem>>;
  definitionContextList: string[] = [];

  recordDefinitions$: Observable<Array<RecordDefinitionDataItem>>;
  recordDefinitionsState$: Observable<RecordDefinitionsState>;

  formulaContexts$: Observable<Array<FormulaContextDataItem>>;
  formulaContextsStats$: Observable<FormulaContextState>;

  worksheetConfigs$: Observable<Array<WorksheetConfigDataItem>>;
  worksheetConfigsStats$: Observable<WorksheetConfigsState>;

  private unsubscribe: Subscription[] = [];

  selectedRecordDefinition$: Observable<RecordDefinitionDataItem>;
  selectedRecordDefinition: RecordDefinitionDataItem;
  selectedDefinition$: Observable<DefinitionDataItem>;
  selectedDefinition: DefinitionDataItem;
  selectedFormulaContext$: Observable<FormulaContextDataItem>;
  selectedFormulaContext: FormulaContextDataItem;
  selectedWorksheetConfig$: Observable<WorksheetConfigDataItem>;
  selectedWorksheetConfig: WorksheetConfigDataItem;
  selectedDefinitionToAdd: DefinitionDataItem;

  types: string[] = ["Formula Context", "Record"];
  selectedType$: Observable<string>;
  selectedType: string;

  editor: Editor;

  constructor(
    private configurationService: ConfigurationService,
    private store: Store<ConfigurationState>) {
  }

  ngOnInit() {
    this.definitionsState$ = this.store.pipe(select(definitions));
    this.definitions$ = this.definitionsState$.pipe(select(selectAllDefinition));

    this.recordDefinitionsState$ = this.store.pipe(select(recordDefinitions));
    this.recordDefinitions$ = this.recordDefinitionsState$.pipe(select(selectAllRecordDefinition));

    this.formulaContextsStats$ = this.store.pipe(select(formulaContexts));
    this.formulaContexts$ = this.formulaContextsStats$.pipe(select(selectAllFormulaContext));

    this.worksheetConfigsStats$ = this.store.pipe(select(worksheetConfigs));
    this.worksheetConfigs$ = this.worksheetConfigsStats$.pipe(select(selectAllWorksheetConfig));

    this.selectedType$ = this.store.pipe(select(selectedType));
    this.unsubscribe.push(
      this.selectedType$.subscribe(selectedType => {
        this.selectedType = selectedType;
      })
    );

    this.selectedRecordDefinition$ = this.store.pipe(select(selectedRecordDefinition));
    this.unsubscribe.push(
      this.selectedRecordDefinition$.subscribe(recordDefinitionDataItem => {
        this.selectedRecordDefinition = recordDefinitionDataItem;
        if (recordDefinitionDataItem !== null) {
          this.definitionContextList = recordDefinitionDataItem.definitionIds;
          this.definitionsForContext$ = this.store.select(selectYourEntityByIds(this.definitionContextList)); // We shouldn't have to redefine this...
        }
      })
    );

    this.selectedDefinition$ = this.store.pipe(select(selectedDefinition));
    this.unsubscribe.push(
      this.selectedDefinition$.subscribe(definitionDataItem => {
        this.selectedDefinition = definitionDataItem;
      })
    );

    this.selectedFormulaContext$ = this.store.pipe(select(selectedFormulaContext));
    this.unsubscribe.push(
      this.selectedFormulaContext$.subscribe(selectedFormulaContext => {
        this.selectedFormulaContext = selectedFormulaContext;
        if (selectedFormulaContext !== null) {
          this.definitionContextList = selectedFormulaContext.definitionIds;
          this.definitionsForContext$ = this.store.select(selectYourEntityByIds(this.definitionContextList)); // We shouldn't have to redefine this...
        }
      })
    );

    this.selectedWorksheetConfig$ = this.store.pipe(select(selectedWorksheetConfig));
    this.unsubscribe.push(
      this.selectedWorksheetConfig$.subscribe(selectedWorksheetConfig => {
        this.selectedWorksheetConfig = selectedWorksheetConfig;
      })
    );

    // ??
    // this.definitionsForContext$ = this.store.select(selectYourEntityByIds(this.definitionContextList));

    this.store.dispatch(new RequestWorksheetConfigs({}));
  }

  ngAfterViewInit() {
    ace.require('ace/ext/language_tools');
    this.editor = ace.edit("editor");
    this.editor.setTheme("ace/theme/monokai");
    this.editor.session.setMode("ace/mode/javascript");
    this.editor.setOptions({
      enableBasicAutocompletion: true,
      enableSnippets: true,
      enableLiveAutocompletion: true
    });
    this.editor.on('change', () => {
      this.store.dispatch(new UpdateDefinition({
        update: {
          id: this.selectedDefinition.uuid,
          changes: {expression: this.editor.getValue()}
        }
      }));
    });
  }

  ngOnDestroy() {
    this.unsubscribe.forEach((sb) => sb.unsubscribe());
  }

  typeChanged() {
    this.store.dispatch(new SelectType({
      selectedType: this.selectedType
    }));
  }

  recordDefinitionChanged() {
    this.store.dispatch(new RequestDefinitions({
      type: 'All',
      recordDefinition: this.selectedRecordDefinition,
      formulaContext: null
    }));
  }

  formulaContextChanged() {
    this.store.dispatch(new RequestDefinitions({
      type: 'All',
      recordDefinition: null,
      formulaContext: this.selectedFormulaContext
    }));
  }

  worksheetConfigChanged() {
    this.store.dispatch(new SelectWorksheetConfig({
      worksheetConfig: this.selectedWorksheetConfig
    }));
  }

  addNewWorksheetConfigDetail() {
    this.store.dispatch(new AddNewWorksheetConfig({
      worksheetConfigName: this.selectedWorksheetConfig.name,
      definitionId: this.selectedDefinitionToAdd.uuid
    }));
  }

  valueChange(worksheetConfigDetail: WebWorksheetConfigDataItem, event: any, field: string) {
    let element = event.target;

    this.store.dispatch(new UpdateWorksheetConfig({
      update: {
        id: worksheetConfigDetail.definitionId,
        changes: {name: element.value}
      }
    }));
  }

  focusOut() {

  }

  definitionChanged() {
    this.editor.setValue(this.selectedDefinition.expression);

    this.store.dispatch(new SelectDefinition({
      definition: this.selectedDefinition
    }));
  }

  save() {
    this.store.dispatch(new SaveDefinition({
      definition: this.selectedDefinition
    }));
  }
}
