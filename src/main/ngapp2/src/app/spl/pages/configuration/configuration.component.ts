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
import {ConfigurationState, DefinitionsState, RecordDefinitionsState} from "./reducers/configuration.reducers";
import {
  RequestDefinitions,
  RequestRecordDefinitions,
  SaveDefinition,
  SelectDefinition,
  UpdateDefinition
} from "./actions/configuration.actions";
import {RecordDefinitionDataItem} from "../../wsObjects/recordDefinitionDataItem";
import {
  definitions,
  recordDefinitions,
  selectedDefinition,
  selectedRecordDefinition, selectYourEntityById
} from "./selectors/configuration.selectors";
import {selectAll as selectAllRecordDefinition} from "./selectors/recordDefinition.selectors";
import {selectAll as selectAllDefinition} from "./selectors/definition.selectors";
import {DefinitionDataItem} from "../../wsObjects/definitionDataItem";
import Editor = Ace.Editor;

// Action

@Component({
  selector: 'configuration',
  templateUrl: './configuration.component.html',
  styleUrls: ['./configuration.component.scss']
})
export class ConfigurationComponent implements OnInit, OnDestroy, AfterViewInit {
  definitions$: Observable<Array<DefinitionDataItem>>;
  definitionsState$: Observable<DefinitionsState>;

  recordDefinitions$: Observable<Array<RecordDefinitionDataItem>>;
  recordDefinitionsState$: Observable<RecordDefinitionsState>;

  private unsubscribe: Subscription[] = [];

  selectedRecordDefinition$: Observable<RecordDefinitionDataItem>;
  selectedRecordDefinition: RecordDefinitionDataItem;
  selectedDefinition$: Observable<DefinitionDataItem>;
  selectedDefinition: DefinitionDataItem;

  editor: Editor;

  constructor(
    private configurationService: ConfigurationService,
    private store: Store<ConfigurationState>) {
  }

  ngOnInit() {
    this.store.dispatch(new RequestRecordDefinitions({}));

    this.definitionsState$ = this.store.pipe(select(definitions));
    this.definitions$ = this.definitionsState$.pipe(select(selectAllDefinition));

    this.recordDefinitionsState$ = this.store.pipe(select(recordDefinitions));
    this.recordDefinitions$ = this.recordDefinitionsState$.pipe(select(selectAllRecordDefinition));

    // selectYourEntityById

    this.selectedRecordDefinition$ = this.store.pipe(select(selectedRecordDefinition));
    this.unsubscribe.push(
      this.selectedRecordDefinition$.subscribe(recordDefinitionDataItem => {
        this.selectedRecordDefinition = recordDefinitionDataItem;
      })
    );

    this.selectedDefinition$ = this.store.pipe(select(selectedDefinition));
    this.unsubscribe.push(
      this.selectedDefinition$.subscribe(definitionDataItem => {
        this.selectedDefinition = definitionDataItem;
      })
    );
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

  recordDefinitionChanged() {
    this.store.dispatch(new RequestDefinitions({
      recordDefinition: this.selectedRecordDefinition
    }));
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
