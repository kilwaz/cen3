import {Component, OnDestroy, OnInit} from '@angular/core';

// RxJS
import {Observable, Subscription} from 'rxjs';

// Store
import {select, Store} from '@ngrx/store';
import {ManagementState} from './reducers/management.reducers';

// Selectors
import {fileData, fileUrl, freeMemory, maxMemory, totalMemory} from './selectors/management.selectors';
import {ManagementService} from './service/management.service';
import {DownloadTestRequest, QueryManagement, UpdateFileURL} from "./actions/management.actions";

// Action

@Component({
  selector: 'management',
  templateUrl: './management.component.html',
  styleUrls: ['./management.component.scss']
})
export class ManagementComponent implements OnInit, OnDestroy {
  totalMemory$: Observable<number>;
  freeMemory$: Observable<number>;
  maxMemory$: Observable<number>;

  fileData$: Observable<Blob>;
  fileUrl$: Observable<string>;

  url: string;
  test: string;

  private unsubscribe: Subscription[] = [];

  constructor(
    private hierarchyService: ManagementService,
    private store: Store<ManagementState>) {
  }

  ngOnInit(): void {
    this.totalMemory$ = this.store.pipe(select(totalMemory));
    this.freeMemory$ = this.store.pipe(select(freeMemory));
    this.maxMemory$ = this.store.pipe(select(maxMemory));

    this.fileData$ = this.store.pipe(select(fileData));
    this.fileUrl$ = this.store.pipe(select(fileUrl));

    this.store.dispatch(new QueryManagement({}));

    this.unsubscribe.push(
      this.fileData$.subscribe(blob => {
        if (blob !== null && blob !== undefined) {
          console.log("Blob is");
          console.log(blob);
          console.log("This was triggered file");

          this.store.dispatch(new UpdateFileURL({
            fileUrl: URL.createObjectURL(blob)
          }));
        }
      })
    );

    this.test = "Hello!";
  }

  ngOnDestroy() {
    this.unsubscribe.forEach((sb) => sb.unsubscribe());
  }

  triggerDownload(): void {
    this.store.dispatch(new DownloadTestRequest({}));
  }
}
