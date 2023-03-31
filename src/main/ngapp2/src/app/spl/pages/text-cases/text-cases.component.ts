import {Component, OnInit, OnDestroy} from '@angular/core';

// RxJS
import {Observable, Subject} from 'rxjs';

// Store
import {select, Store} from '@ngrx/store';
import {TextCasesState} from './reducers/text-cases.reducers';

// Selectors
import {fileToUpload, textFunction, textResult, textToProcess} from './selectors/text-cases.selectors';
import {TextCasesService} from './service/text-cases.service';

// Action
import {TextToProcessUpdated, ProcessText, FilePicked} from './actions/text-cases.actions';

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
  fileToUpload$: Observable<File>;

  private unsubscribe: Subject<any>;

  constructor(
    private textCasesService: TextCasesService,
    private store: Store<TextCasesState>) {

    this.unsubscribe = new Subject();
  }

  ngOnInit(): void {
    this.textToProcess$ = this.store.pipe(select(textToProcess));
    this.textResult$ = this.store.pipe(select(textResult));
    this.textFunction$ = this.store.pipe(select(textFunction));
    this.fileToUpload$ = this.store.pipe(select(fileToUpload));
  }

  processText(textFunctionProvided: number) {
    this.store.dispatch(new ProcessText({
      textFunction: textFunctionProvided
    }));
  }

  textToProcessChange(event: Event) {
    const target = event.target as HTMLInputElement;
    if (target) {
      this.store.dispatch(new TextToProcessUpdated({
        textToProcess: target.value
      }));
    }
  }

  ngOnDestroy(): void {
    this.unsubscribe.complete();
  }

  onFileSelected(event): void {
    const file: File = event.target.files[0];

    if (file) {
      this.store.dispatch(new FilePicked({
        fileToUpload: file
      }));
    }
  }
}
