import {Component, OnInit, OnDestroy} from '@angular/core';

// RxJS
import {combineLatest, forkJoin, Observable, Subject} from 'rxjs';

// Store
import {select, Store} from '@ngrx/store';
import {ToolState} from './reducers/tool.reducers';

// Selectors
import {ageDays, ageMonths, ageYears, ageOnDate, startDate} from './selectors/tool.selectors';
import {ToolService} from './service/tool.service';
import {finalize, takeUntil, tap, map, withLatestFrom} from 'rxjs/operators';

// Action
import {StartDateChange, AgeOnDateChange, Test} from './actions/tool.actions';
import {MatDatepickerInputEvent} from '@angular/material/datepicker';

@Component({
  selector: 'app-tool',
  templateUrl: './tool.component.html',
  styleUrls: ['./tool.component.scss']
})
export class ToolComponent implements OnInit, OnDestroy {
  tick: boolean;
  ageDays$: Observable<number>;
  ageMonths$: Observable<number>;
  ageYears$: Observable<number>;
  ageResult$: Observable<number>;
  startDate$: Observable<Date>;
  ageOnDate$: Observable<Date>;

  private unsubscribe: Subject<any>;

  constructor(
    private toolService: ToolService,
    private store: Store<ToolState>) {

    this.unsubscribe = new Subject();
  }

  ngOnInit(): void {
    this.tick = true;

    // @ts-ignore
    this.ageDays$ = this.store.pipe(select(ageDays));
    // @ts-ignore
    this.ageMonths$ = this.store.pipe(select(ageMonths));
    // @ts-ignore
    this.ageYears$ = this.store.pipe(select(ageYears));
    // @ts-ignore
    this.startDate$ = this.store.pipe(select(startDate));
    // @ts-ignore
    this.ageOnDate$ = this.store.pipe(select(ageOnDate));

    combineLatest(this.startDate$, this.ageOnDate$).pipe(
      map(([startDate, ageOnDate]) => ({startDate, ageOnDate})),
    ).subscribe(x => {
      if (x.startDate != null && x.ageOnDate != null) {
        this.toolService
          .test(x.startDate.getDate(), x.startDate.getMonth() + 1, x.startDate.getFullYear(), x.ageOnDate.getDate(), x.ageOnDate.getMonth() + 1, x.ageOnDate.getFullYear())
          .pipe(
            tap(test => {
              this.store.dispatch(new Test({
                ageDays: test.ageDays,
                ageMonths: test.ageMonths,
                ageYears: test.ageYears
              }));
            }),
            takeUntil(this.unsubscribe),
            finalize(() => {
              // this.cdr.markForCheck();
            })
          ).subscribe();
      }
    });
  }

  startDateChange(event: MatDatepickerInputEvent<Date>) {
    this.store.dispatch(new StartDateChange({
      startDate: event.value
    }));
  }

  ageOnDateChange(event: MatDatepickerInputEvent<Date>) {
    this.store.dispatch(new AgeOnDateChange({
      ageOnDate: event.value
    }));
  }

  ngOnDestroy(): void {
    this.unsubscribe.next();
    this.unsubscribe.complete();
  }
}
