import {Component, OnInit, OnDestroy} from '@angular/core';

// RxJS
import {Observable, Subject} from 'rxjs';

// Store
import {select, Store} from '@ngrx/store';
import {ToolState} from './reducers/tool.reducers';

// Selectors
import {message} from './selectors/tool.selectors';
import {ToolService} from './service/tool.service';
import {finalize, takeUntil, tap} from 'rxjs/operators';

// Action
import {Test} from './actions/tool.actions';

@Component({
  selector: 'app-tool',
  templateUrl: './tool.component.html',
  styleUrls: ['./tool.component.scss']
})
export class ToolComponent implements OnInit, OnDestroy {
  tick: boolean;
  message$: Observable<string>;

  private unsubscribe: Subject<any>;

  constructor(
    private toolService: ToolService,
    private store: Store<ToolState>) {

    this.unsubscribe = new Subject();
  }

  ngOnInit(): void {
    this.tick = true;

    // @ts-ignore
    this.message$ = this.store.pipe(select(message));
  }

  testMessage() {
    this.toolService
      .test('How are you doing today server?')
      .pipe(
        tap(test => {
          console.log('Response from server' + test.message);
          this.store.dispatch(new Test({message: test.message}));
        }),
        takeUntil(this.unsubscribe),
        finalize(() => {
          // this.cdr.markForCheck();
        })
      )
      .subscribe();
  }

  ngOnDestroy(): void {
    this.unsubscribe.next();
    this.unsubscribe.complete();
  }
}
