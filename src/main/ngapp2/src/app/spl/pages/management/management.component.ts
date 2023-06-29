import {AfterViewInit, Component, OnDestroy, OnInit} from '@angular/core';

// RxJS
import {Observable, Subscription} from 'rxjs';
import * as ace from "ace-builds";

// Store
import {select, Store} from '@ngrx/store';
import {ManagementState} from './reducers/management.reducers';

// Selectors
import {freeMemory, maxMemory, totalMemory} from './selectors/management.selectors';
import {ManagementService} from './service/management.service';
import {DownloadTestRequest, QueryManagement} from "./actions/management.actions";

// Action

@Component({
  selector: 'management',
  templateUrl: './management.component.html',
  styleUrls: ['./management.component.scss']
})
export class ManagementComponent implements OnInit, OnDestroy, AfterViewInit {
  totalMemory$: Observable<number>;
  freeMemory$: Observable<number>;
  maxMemory$: Observable<number>;

  private unsubscribe: Subscription[] = [];

  constructor(
    private hierarchyService: ManagementService,
    private store: Store<ManagementState>) {
  }

  ngOnInit(): void {
    this.totalMemory$ = this.store.pipe(select(totalMemory));
    this.freeMemory$ = this.store.pipe(select(freeMemory));
    this.maxMemory$ = this.store.pipe(select(maxMemory));

    this.store.dispatch(new QueryManagement({}));
  }

  ngAfterViewInit() {
    ace.require('ace/ext/language_tools');
    const editor = ace.edit("editor");
    editor.setTheme("ace/theme/monokai");
    editor.session.setMode("ace/mode/javascript");
    editor.setOptions({
      enableBasicAutocompletion: true,
      enableSnippets: true,
      enableLiveAutocompletion: true
    });
  }

  ngOnDestroy() {
    this.unsubscribe.forEach((sb) => sb.unsubscribe());
  }

  triggerDownload(): void {
    this.store.dispatch(new DownloadTestRequest({}));
  }
}
