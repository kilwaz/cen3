import {Component, OnInit, OnDestroy} from '@angular/core';

// RxJS
import {Observable, Subject} from 'rxjs';

// Store
import {select, Store} from '@ngrx/store';
import {TextCasesState} from './reducers/text-cases.reducers';

// Selectors
import {textFunction, textResult, textToProcess} from './selectors/text-cases.selectors';
import {TextCasesService} from './service/text-cases.service';

// Action
import {TextToProcessUpdated, ProcessText} from './actions/text-cases.actions';

@Component({
  selector: 'app-text-cases',
  templateUrl: './text-cases.component.html',
  styleUrls: ['./text-cases.component.scss']
})
export class TextCasesComponent implements OnInit, OnDestroy {
  SENTENCE_CASE = 1;
  LOWER_CASE = 2;
  UPPER_CASE = 3;
  CAPITALISED_CASE = 4;
  ALTERNATING_CASE = 5;
  TITLE_CASE = 6;
  INVERSE_CASE = 7;

  textToProcess$: Observable<string>;
  textResult$: Observable<string>;
  textFunction$: Observable<number>;

  private unsubscribe: Subject<any>;

  constructor(
    private textCasesService: TextCasesService,
    private store: Store<TextCasesState>) {

    this.unsubscribe = new Subject();
  }

  ngOnInit(): void {
    // @ts-ignore
    this.textToProcess$ = this.store.pipe(select(textToProcess));
    // @ts-ignore
    this.textResult$ = this.store.pipe(select(textResult));
    // @ts-ignore
    this.textFunction$ = this.store.pipe(select(textFunction));
  }

  processText(textFunctionProvided: number) {
    this.store.dispatch(new ProcessText({
      textFunction: textFunctionProvided
    }));
  }

  textToProcessChange(newValue: string) {
    this.store.dispatch(new TextToProcessUpdated({
      textToProcess: newValue
    }));
  }

  ngOnDestroy(): void {
    this.unsubscribe.next();
    this.unsubscribe.complete();
  }
}
