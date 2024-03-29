import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {WorksheetComponent} from './worksheet.component';
import {FormsModule} from '@angular/forms';
import {NgbNavModule, NgbTooltipModule} from '@ng-bootstrap/ng-bootstrap';
import {InlineSVGModule} from 'ng-inline-svg-2';
import {StoreModule} from '@ngrx/store';
import {worksheetReducer} from './reducers/worksheet.reducers';
import {WorksheetEffects} from './effects/worksheet.effects';
import {WorksheetService} from './service/worksheet.service';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatButtonModule} from '@angular/material/button';
import {EffectsModule} from '@ngrx/effects';
import {WorksheetTableComponent} from "./components/worksheet-table/worksheet-table.component";
import {WorksheetHeaderComponent} from "./components/worksheet-header/worksheet-header.component";
import {WorksheetRowComponent} from "./components/worksheet-row/worksheet-row.component";
import {WorksheetCellComponent} from "./components/worksheet-cell/worksheet-cell.component";
import {WorksheetHeaderCellComponent} from "./components/worksheet-header-cell/worksheet-header-cell.component";
import {WorksheetSortFilterComponent} from "./components/worksheet-sort-filter/worksheet-sort-filter.component";
import {WorksheetSummaryComponent} from "./components/worksheet-summary/worksheet-summary.component";
import {ClickStopPropagation} from "./directives/click-stop-propagation";
import {SortFilterService} from "./service/sort-filter.service";
import {UpdateService} from "./service/update.service";
import {WorksheetPaginationComponent} from "./components/worksheet-pagination/worksheet-pagination.component";
import {ScrollingModule} from "@angular/cdk/scrolling";
import {summaryReducer} from "./reducers/summary.reducers";
import {SummaryEffects} from "./effects/summary.effects";
import {SummaryService} from "./service/summary.service";

@NgModule({
  declarations: [
    WorksheetComponent,
    WorksheetTableComponent,
    WorksheetHeaderComponent,
    WorksheetRowComponent,
    WorksheetCellComponent,
    WorksheetHeaderCellComponent,
    WorksheetSortFilterComponent,
    WorksheetSummaryComponent,
    WorksheetPaginationComponent,
    ClickStopPropagation
  ],
  providers: [
    WorksheetService,
    SortFilterService,
    UpdateService,
    SummaryService
  ],
  imports: [
    CommonModule,
    FormsModule,
    InlineSVGModule,
    NgbTooltipModule,

    MatButtonModule,
    MatFormFieldModule,
    NgbNavModule,
    MatInputModule,

    ScrollingModule,

    StoreModule.forFeature('worksheet', worksheetReducer),
    StoreModule.forFeature('summary', summaryReducer),
    EffectsModule.forFeature([WorksheetEffects, SummaryEffects]),

    RouterModule.forChild([
      {
        path: '',
        component: WorksheetComponent,
      },
    ]),
  ],
})
export class WorksheetModule {
}
